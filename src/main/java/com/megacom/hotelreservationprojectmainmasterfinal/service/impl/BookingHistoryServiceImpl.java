package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.BookingHistoryDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.HotelDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.BookingHistoryMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.BookingHistory;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired private BookingHistoryDao bookingHistoryDao;
    @Autowired private HotelDao hotelDao;

    private final String pattern = "dd.MM.yyyy";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private BookingHistoryMapper bookingHistoryMapper = BookingHistoryMapper.INSTANCE;

    @Override
    public BookingHistoryDto save(BookingHistoryDto bookingHistoryDto) {
        BookingHistory bookingHistory = bookingHistoryMapper.toEntity(bookingHistoryDto);
        BookingHistory bookingHistorySaved = bookingHistoryDao.save(bookingHistory);
        return bookingHistoryMapper.toDto(bookingHistorySaved);
    }

    @Override
    public BookingHistoryDto findById(Long id) {
        BookingHistory bookingHistory = bookingHistoryDao.findById(id).orElse(null);
        return bookingHistoryMapper.toDto(bookingHistory);
    }

    @Override
    public ResponseEntity<?> findAllBookingHistoryByBooking(Long hotelId) {
        boolean isExists = hotelDao.existsById(hotelId);
        if (!isExists) {
            return new ResponseEntity<>(Message.of("hotel not found"), HttpStatus.NOT_FOUND);
        } else {
            List<BookingHistory> bookingHistoryList = bookingHistoryDao.findAll();
            return new ResponseEntity<>(bookingHistoryList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> findBookingByDate(Date checkIn, Date checkOut) {
        List<BookingHistory> bookingHistoryList = bookingHistoryDao.findAll();
        if (bookingHistoryList == null) {
            return new ResponseEntity<>(Message.of("Booking history not found"), HttpStatus.NO_CONTENT);
        } else {
            bookingHistoryList.stream().forEach(x -> {
                if (!x.getCheckInDate().equals(checkIn) && !x.getCheckOutDate().equals(checkOut)
                    || !x.getCheckInDate().after(checkIn) && !x.getCheckOutDate().before(checkOut)
                    || !x.getCheckInDate().after(checkIn) && !x.getCheckOutDate().equals(checkOut)
                    || !x.getCheckInDate().equals(checkIn) && !x.getCheckOutDate().before(checkOut)) {
                    bookingHistoryList.remove(x);
                }
            });
            return new ResponseEntity<>(bookingHistoryList, HttpStatus.OK);
        }
    }
}
