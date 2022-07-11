package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.CityDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.service.CityService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired private HotelService hotelService;
    @Autowired private CityService cityService;

    @PostMapping("/saveCity") // works
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto) {
        return cityService.save(cityDto);
    }

    @PutMapping("/updateCity") // works
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto) {
        return cityService.update(cityDto);
    }

    @DeleteMapping("/deleteCity") // works
    public ResponseEntity<?> deleteCity(@RequestBody CityDto cityDto) {
        return cityService.delete(cityDto);
    }

    @PostMapping("/saveHotel") // works
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.save(hotelDto);
    }

    @PutMapping("/update/hotel") // works
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.update(hotelDto);
    }

    @DeleteMapping("/delete/hotel") // works
    public ResponseEntity<?> deleteHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.delete(hotelDto);
    }

    @PutMapping("/setActive")
    ResponseEntity<?> setActive(@RequestBody HotelDto hotelDto) {
        return hotelService.setActive(hotelDto);
    }

    @PostMapping("/blockHotel")
    ResponseEntity<?> block(@RequestBody HotelDto hotelDto) {
        return hotelService.block(hotelDto);
    }

    @GetMapping("/findAllCities")
    ResponseEntity<?> findAllCities() {
        return cityService.findAll();
    }
}
