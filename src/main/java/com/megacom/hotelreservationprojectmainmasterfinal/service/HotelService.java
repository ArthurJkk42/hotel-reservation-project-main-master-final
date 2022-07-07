package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface HotelService {

    ResponseEntity<?> save(HotelDto hotelDto);

    HotelDto findById(Long id);

    ResponseEntity<?> update(HotelDto hotelDto);

    ResponseEntity<?> setActive(HotelDto hotelDto);

    ResponseEntity<?> delete(HotelDto hotelDto); // required

    ResponseEntity<?> block(HotelDto hotelDto); // required

    void countCurrentScore(); // required

    List<HotelDto> findAll();

    ResponseEntity<?> findHotelsByCityIdAndRating(Long cityId); // required

    ResponseEntity<?> filterByCity(Long cityId, Date checkInDate, Date checkOutDate,
                                   int guestCount, EBedType bedType);
}
