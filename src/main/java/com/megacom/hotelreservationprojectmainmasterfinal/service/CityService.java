package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.CityDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface CityService {

    ResponseEntity<?> save(CityDto cityDto);

    ResponseEntity<?> update(CityDto cityDto);

    CityDto findById(Long cityId);

    ResponseEntity<?> findFilteredByCity(Long cityId, Date checkInDate, Date checkOutDate,
                                         int guestCount, int roomCount);

    ResponseEntity<?> findAll(); // по алфавиту
}
