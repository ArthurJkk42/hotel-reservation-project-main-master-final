package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.HotelDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.PriceDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.RoomDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.HotelMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.RoomMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.*;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EHotelStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelFilterResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.RoomFilterResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private PriceService priceService;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(HotelDto hotelDto) {
        UserDto managerSaved = userService.save(hotelDto.getManager());
        hotelDto.setManager(managerSaved);
        Hotel savedHotel = hotelDao.save(hotelMapper.toEntity(hotelDto));
        return new ResponseEntity<>(hotelMapper.toDto(savedHotel), HttpStatus.OK);
    }

    @Override
    public HotelDto findById(Long id) {
        Hotel hotel = hotelDao.findById(id).orElse(null);
        return hotelMapper.toDto(hotel);
    }

    @Override
    public ResponseEntity<?> update(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        } else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            Hotel updatedHotel = hotelDao.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> setActive(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Hotel not deleted"), HttpStatus.NOT_FOUND);
        } else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            hotel.setHotelStatus(EHotelStatus.ACTIVE);
            Hotel updatedHotel = hotelDao.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Hotel not deleted"), HttpStatus.NOT_FOUND);
        } else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            hotel.setHotelStatus(EHotelStatus.DELETED);
            Hotel updatedHotel = hotelDao.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> block(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Hotel not blocked"), HttpStatus.NOT_FOUND);
        } else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            hotel.setHotelStatus(EHotelStatus.BLOCKED);
            Hotel updatedHotel = hotelDao.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public void countCurrentScore() {
        List<HotelDto> hotelDtoList = findAll();
        hotelDtoList.stream().forEach(x -> {
            if (x.getHotelStatus().equals(EHotelStatus.BLOCKED)
                    || x.getHotelStatus().equals(EHotelStatus.DELETED)) {
                hotelDtoList.remove(x);
            }
            List<ReviewDto> reviewDtoList = reviewService.findAllByHotelAndActive(x.getId());
            Double sum = reviewDtoList.stream().mapToDouble(ReviewDto::getScore).sum();
            Double currentScore = Math.round(sum / reviewDtoList.size()) * 1.0;

            // на случай если double округление не работает
            String result = String.format("%.1f", currentScore);


            x.setCurrentScore(currentScore);
            update(x);
        });
    }

    @Override
    public List<HotelDto> findAll() {
        return hotelMapper.toDtoList(hotelDao.findAll());
    }

    @Override
    public ResponseEntity<?> findHotelsByCityIdAndRating(Long cityId) {
        CityDto cityDto = cityService.findById(cityId);
        if (cityDto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // null, 404
        } else {
            List<Hotel> hotelList = hotelDao.findHotelsByCityId(cityId);
            List<HotelResponse> hotelResponses = hotelMapper.convertToResponseList(hotelList);
            hotelResponses.stream()
                    .sorted(Comparator.comparingDouble(HotelResponse::getCurrentScore))
                    .forEach(p -> hotelResponses.add(p));
            return new ResponseEntity<>(hotelResponses, HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> filterByCity(Long cityId, Date checkInDate,
                                          Date checkOutDate, int guestCount, EBedType bedType) {
        List<Hotel> hotels = hotelDao.findAllByCityAndBedType(cityId, bedType);
        List<Hotel> availableHotels = new ArrayList<>();
        List<HotelFilterResponse> filteredHotels = new ArrayList<>();
        hotels.stream().forEach(x -> {
            List<RoomDto> rooms = roomService.findAllRoomsByHotel(x, bedType, guestCount);
            List<RoomDto> availableRooms = new ArrayList<>();
            rooms.stream().forEach(y -> {
                List<BookingDto> bookings = bookingService.findAllByRoomAndActive(y);
                if (bookings.isEmpty()) {
                    availableRooms.add(y);
                } else {
                    AtomicBoolean isBooked = new AtomicBoolean(false);
                    bookings.stream().forEach(z -> {
                        if (checkIsBooked(z, checkInDate, checkOutDate)) {
                            isBooked.set(true);
                        }
                    });
                    if (isBooked.equals(false)) {
                        availableRooms.add(y);
                    }
                }
            });

            if (!availableRooms.isEmpty()) {
                availableHotels.add(x);
                HotelFilterResponse response = formHotelResponse(x, availableRooms, checkInDate, checkOutDate);
                filteredHotels.add(response);
            }
        });
        return new ResponseEntity<>(filteredHotels, HttpStatus.OK);
    }

    private boolean checkIsBooked(BookingDto bookingDto, Date startDate, Date endDate) {
        if (startDate.equals(bookingDto.getCheckInDate())
                || startDate.equals(bookingDto.getCheckOutDate())
                || (startDate.after(bookingDto.getCheckInDate()) && startDate.before(bookingDto.getCheckOutDate()))
                || endDate.equals(bookingDto.getCheckInDate())
                || endDate.equals(bookingDto.getCheckOutDate())
                || (endDate.after(bookingDto.getCheckInDate()) && endDate.before(bookingDto.getCheckOutDate()))
                || (startDate.before(bookingDto.getCheckInDate()) && endDate.after(bookingDto.getCheckOutDate()))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private HotelFilterResponse formHotelResponse(Hotel hotel, List<RoomDto> roomDtos, Date checkIn, Date checkOut) {
        HotelFilterResponse hotelResponse = new HotelFilterResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setAddress(hotel.getAddress());
        hotelResponse.setCurrentScore(hotel.getCurrentScore());
        hotelResponse.setDescription(hotel.getDescription());
        hotelResponse.setEmail(hotel.getEmail());
        hotelResponse.setPhone(hotelResponse.getPhone());
        hotelResponse.setName(hotelResponse.getName());

        List<RoomFilterResponse> roomResponse = new ArrayList<>();
        roomDtos.stream().forEach(room -> {
            PriceDto priceForCheckIn = priceService.findPriceByRoom(room.getRoomCategory().getId(), checkIn);
            PriceDto priceForCheckOut = priceService.findPriceByRoom(room.getRoomCategory().getId(), checkOut);
            if (priceForCheckIn != null && priceForCheckOut != null) {
                if (priceForCheckIn.getPrice() == priceForCheckOut.getPrice()) {
                    long diff = checkOut.getTime() - checkIn.getTime();
                    int daysBetween = (int) (diff / (1000*60*60*24));
                    System.out.println(daysBetween);
                    double totalSum = priceForCheckIn.getPrice() * daysBetween;

                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .wifi(room.isWifi())
                            .view(room.getView())
                            .price(totalSum)
                            .build();

                    roomResponse.add(roomFilterResponse);
                } else {
                    long diff = checkOut.getTime() - priceForCheckIn.getEndDate().getTime();
                    int daysBetween = (int) (diff / (1000*60*60*24));
                    double sumBeginning = daysBetween * priceForCheckIn.getPrice();
                    long diff2 = checkOut.getTime() - priceForCheckOut.getStartDate().getTime();
                    int daysBetween2 = (int) (diff2 / (1000*60*60*24));
                    double sumEnding = daysBetween2 * priceForCheckOut.getPrice();
                    double totalSum = sumBeginning + sumEnding;

                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .wifi(room.isWifi())
                            .view(room.getView())
                            .price(totalSum)
                            .build();

                    roomResponse.add(roomFilterResponse);
                }
            }

        });

        hotelResponse.setAvailableRooms(roomResponse);
        return hotelResponse;
    }

    @Override
    public ResponseEntity<?> filterByRating(List<HotelFilterResponse> filteredHotels, double rating) {
        filteredHotels.stream().forEach(x -> {
            if (x.getCurrentScore() < rating) {
                filteredHotels.remove(x);
            }
        });
        return new ResponseEntity<>(filteredHotels, HttpStatus.OK);
    }
}
