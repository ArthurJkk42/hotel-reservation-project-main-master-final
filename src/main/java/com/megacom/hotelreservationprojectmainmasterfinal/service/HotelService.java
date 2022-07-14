package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelFilterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface HotelService {

    ResponseEntity<?> save(HotelDto hotelDto);

    ResponseEntity<?> update(HotelDto hotelDto);

    ResponseEntity<?> setActive(HotelDto hotelDto);

    ResponseEntity<?> delete(HotelDto hotelDto);

    ResponseEntity<?> block(HotelDto hotelDto);

    HotelDto findById(Long id);

    void countCurrentScore();

    List<HotelDto> findAll();

    ResponseEntity<?> findHotelsByCityIdAndRating(Long cityId);

    ResponseEntity<?> filterByCity(Long cityId, Date checkInDate, Date checkOutDate,
                                   int guestCount, EBedType bedType);

    ResponseEntity<?> filterByRating(List<HotelFilterResponse> filteredHotels, double rating);
}
