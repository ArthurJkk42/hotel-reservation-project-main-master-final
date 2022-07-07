package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.BookingHistoryDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.BookingHistoryMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.BookingHistory;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired
    private BookingHistoryDao bookingHistoryDao;

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
    public List<BookingHistoryDto> findAllBookingHistoryByBooking(BookingDto bookingDto) {
        return null;
    }
}
