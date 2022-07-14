package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface BookingHistoryService {

    BookingHistoryDto save(BookingHistoryDto bookingHistoryDto);

    BookingHistoryDto findById(Long id);

    ResponseEntity<?> findAllBookingHistoryByBooking(Long hotelId);

    ResponseEntity<?> findBookingByDate(Date checkIn, Date checkOut);
}
