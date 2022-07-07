package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;

import java.util.List;

public interface BookingHistoryService {

    BookingHistoryDto save(BookingHistoryDto bookingHistoryDto);

    BookingHistoryDto findById(Long id);

    List<BookingHistoryDto> findAllBookingHistoryByBooking(BookingDto bookingDto);
}
