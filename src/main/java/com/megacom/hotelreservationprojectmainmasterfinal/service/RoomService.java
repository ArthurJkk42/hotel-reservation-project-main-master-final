package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    ResponseEntity<?> save(RoomDto roomDto);

    RoomDto findById(Long id);

    ResponseEntity<?> update(RoomDto roomDto);

    List<RoomDto> filterByBedType(EBedType eBedType); // required

    List<RoomDto> findAllRoomsByHotel(Hotel hotel, EBedType bedType, int capacity);

    List<RoomDto> findByBooking(Long id);

    List<RoomDto> findRoomsByHotel(Long id);

    List<RoomDto> findByHotelGuestId(Long id);
}
