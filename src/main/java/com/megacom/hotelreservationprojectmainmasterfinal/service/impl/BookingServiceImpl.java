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

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingHistoryService bookingHistoryService;

    BookingMapper bookingMapper = BookingMapper.INSTANCE;
    RoomMapper roomMapper = RoomMapper.INSTANCE;
    UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(BookingDto bookingDto) {
        try {
            BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
            bookingHistoryDto.setBooking(bookingDto);
            bookingHistoryDto.setChangeDate(null);
            bookingHistoryDto.setComment(bookingDto.getComment());
            bookingHistoryDto.setUser(bookingDto.getUser()); //TODO
            bookingHistoryDto.setCheckInDate(bookingDto.getCheckInDate());
            bookingHistoryDto.setCheckOutDate(bookingDto.getCheckOutDate());
            bookingHistoryDto.setGuest(bookingDto.getUser());
            bookingHistoryDto.setStatus(bookingDto.getStatus());
            bookingHistoryDto.setSum(bookingDto.getSum());

            BookingHistoryDto savedBookingHistory = bookingHistoryService.save(bookingHistoryDto);

            Booking booking = bookingMapper.toEntity(bookingDto);
            booking.setStatus(EBookingStatus.BOOKED);
            Booking savedBooking = bookingDao.save(booking);
            return new ResponseEntity<>(savedBooking, HttpStatus.OK);
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
    @Transactional
    public ResponseEntity<?> update(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Booking not found"), HttpStatus.NOT_FOUND);
        } else {
            try {
                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(bookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(bookingDto.getComment());
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setCheckInDate(bookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(bookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setStatus(bookingDto.getStatus());
                bookingHistoryDto.setSum(bookingDto.getSum());

                BookingHistoryDto updatedBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                Booking booking = bookingMapper.toEntity(bookingDto);
                booking.setStatus(EBookingStatus.BOOKED);
                Booking savedBooking = bookingDao.save(booking);
                return new ResponseEntity<>(savedBooking, HttpStatus.OK);
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
                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(bookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(comment);
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setCheckInDate(bookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(bookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setStatus(bookingDto.getStatus());
                bookingHistoryDto.setSum(bookingDto.getSum());

                BookingHistoryDto cancelledBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                Booking booking = bookingMapper.toEntity(bookingDto);
                booking.setStatus(EBookingStatus.CANCELLED);
                Booking canceledBooking = bookingDao.save(booking);
                return new ResponseEntity<>(canceledBooking, HttpStatus.OK);
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
                BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
                bookingHistoryDto.setBooking(bookingDto);
                bookingHistoryDto.setChangeDate(new Date());
                bookingHistoryDto.setComment(bookingDto.getComment());
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setCheckInDate(bookingDto.getCheckInDate());
                bookingHistoryDto.setCheckOutDate(bookingDto.getCheckOutDate());
                bookingHistoryDto.setUser(bookingDto.getUser());
                bookingHistoryDto.setStatus(bookingDto.getStatus());
                bookingHistoryDto.setSum(bookingDto.getSum());

                BookingHistoryDto deletedBookingHistoryDto = bookingHistoryService.save(bookingHistoryDto);

                Booking booking = bookingMapper.toEntity(bookingDto);
                booking.setStatus(EBookingStatus.CANCELLED);
                Booking canceledBooking = bookingDao.save(booking);
                return new ResponseEntity<>(canceledBooking, HttpStatus.OK);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
