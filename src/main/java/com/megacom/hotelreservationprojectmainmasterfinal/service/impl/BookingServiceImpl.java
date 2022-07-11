package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.BookingDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.BookingMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.RoomMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.UserMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Booking;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingHistoryService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private BookingDao bookingDao;
    @Autowired private UserService userService;
    @Autowired private BookingHistoryService bookingHistoryService;

    BookingMapper bookingMapper = BookingMapper.INSTANCE;
    RoomMapper roomMapper = RoomMapper.INSTANCE;
    UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(BookingDto bookingDto) {
        try {
            if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())
                || bookingDto.getCheckInDate().equals(bookingDto.getCheckOutDate())
                || bookingDto.getCheckOutDate().before(bookingDto.getCheckInDate())) {
                return new ResponseEntity<>(Message.of("Invalid date input"), HttpStatus.NOT_ACCEPTABLE);
            }
            Booking booking = bookingMapper.toEntity(bookingDto);
            booking.setStatus(EBookingStatus.BOOKED);
            Booking savedBooking = bookingDao.save(booking);
            BookingDto savedBookingDto = bookingMapper.toDto(savedBooking);

            BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
            bookingHistoryDto.setBooking(savedBookingDto);
            bookingHistoryDto.setChangeDate(new Date());
            bookingHistoryDto.setComment(savedBookingDto.getComment());
            bookingHistoryDto.setUser(savedBookingDto.getUser());
            bookingHistoryDto.setCheckInDate(savedBookingDto.getCheckInDate());
            bookingHistoryDto.setCheckOutDate(savedBookingDto.getCheckOutDate());
            bookingHistoryDto.setGuest(savedBookingDto.getUser());
            bookingHistoryDto.setStatus(savedBookingDto.getStatus());
            bookingHistoryDto.setSum(savedBookingDto.getSum());
            BookingHistoryDto savedBookingHistory = bookingHistoryService.save(bookingHistoryDto);
            return new ResponseEntity<>(savedBookingDto, HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public BookingDto findById(Long id) {
        Booking booking = bookingDao.findById(id).orElse(null);
        return bookingMapper.toDto(booking);
    }

    @Override
    public List<BookingDto> findAllByRoomAndActive(RoomDto room) {
        List<Booking> bookings = bookingDao.findAllByRoomAndStatus(roomMapper.toEntity(room), EBookingStatus.BOOKED);
        return bookingMapper.toDtoList(bookings);
    }

    @Override
//    @Transactional
    public ResponseEntity<?> update(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Booking not found"), HttpStatus.NOT_FOUND);
        } else {
            try {
                if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckInDate().equals(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckOutDate().before(bookingDto.getCheckInDate())) {
                    return new ResponseEntity<>(Message.of("Invalid date input"), HttpStatus.NOT_ACCEPTABLE);
                }
                Booking booking = bookingMapper.toEntity(bookingDto);
                Booking savedBooking = bookingDao.save(booking);
                BookingDto savedBookingDto  = bookingMapper.toDto(savedBooking);

                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(savedBookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(savedBookingDto.getComment());
                bookingHistoryDto.setGuest(savedBookingDto.getUser());
                bookingHistoryDto.setCheckInDate(savedBookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(savedBookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(savedBookingDto.getUser());
                bookingHistoryDto.setStatus(savedBookingDto.getStatus());
                bookingHistoryDto.setSum(savedBookingDto.getSum());
                BookingHistoryDto updatedBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                return new ResponseEntity<>(savedBookingDto, HttpStatus.OK);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> cancel(BookingDto bookingDto, String comment, Long userId) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Booking not found"), HttpStatus.NOT_FOUND);
        } else {
            try {
                if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckInDate().equals(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckOutDate().before(bookingDto.getCheckInDate())) {
                    return new ResponseEntity<>(Message.of("Invalid date input"), HttpStatus.NOT_ACCEPTABLE);
                }
                Booking booking = bookingMapper.toEntity(bookingDto);
                booking.setStatus(EBookingStatus.CANCELLED);
                Booking savedBooking = bookingDao.save(booking);
                BookingDto savedBookingDto  = bookingMapper.toDto(savedBooking);

                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(savedBookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(savedBookingDto.getComment());
                bookingHistoryDto.setGuest(savedBookingDto.getUser());
                bookingHistoryDto.setCheckInDate(savedBookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(savedBookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(savedBookingDto.getUser());
                bookingHistoryDto.setStatus(savedBookingDto.getStatus());
                bookingHistoryDto.setSum(savedBookingDto.getSum());
                BookingHistoryDto updatedBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                return new ResponseEntity<>(savedBookingDto, HttpStatus.OK);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Booking not found"), HttpStatus.NOT_FOUND);
        } else {
            try {
                if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckInDate().equals(bookingDto.getCheckOutDate())
                        || bookingDto.getCheckOutDate().before(bookingDto.getCheckInDate())) {
                    return new ResponseEntity<>(Message.of("Invalid date input"), HttpStatus.NOT_ACCEPTABLE);
                }
                Booking booking = bookingMapper.toEntity(bookingDto);
                booking.setStatus(EBookingStatus.DELETED);
                Booking savedBooking = bookingDao.save(booking);
                BookingDto savedBookingDto  = bookingMapper.toDto(savedBooking);

                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(savedBookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(savedBookingDto.getComment());
                bookingHistoryDto.setGuest(savedBookingDto.getUser());
                bookingHistoryDto.setCheckInDate(savedBookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(savedBookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(savedBookingDto.getUser());
                bookingHistoryDto.setStatus(savedBookingDto.getStatus());
                bookingHistoryDto.setSum(savedBookingDto.getSum());
                BookingHistoryDto updatedBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                return new ResponseEntity<>(savedBookingDto, HttpStatus.OK);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
