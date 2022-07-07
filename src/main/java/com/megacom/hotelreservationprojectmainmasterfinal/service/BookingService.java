package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<?> save(BookingDto bookingDto); // required

    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> cancel(BookingDto bookingDto, String comment, Long userId);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findById(Long id); // required

    List<BookingDto> findAllByRoomAndActive(RoomDto room);
}
