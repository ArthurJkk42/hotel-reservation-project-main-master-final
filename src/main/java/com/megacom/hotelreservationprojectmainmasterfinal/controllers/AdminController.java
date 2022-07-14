package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.CityDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.service.CityService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired private HotelService hotelService;
    @Autowired private CityService cityService;

    @PostMapping("/save/city") // works
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto) {
        return cityService.save(cityDto);
    }

    @PutMapping("/update/city") // works
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto) {
        return cityService.update(cityDto);
    }

    @DeleteMapping("/delete/city") // works
    public ResponseEntity<?> deleteCity(@RequestBody CityDto cityDto) {
        return cityService.delete(cityDto);
    }

    @PutMapping("/set/active") // works
    ResponseEntity<?> setActive(@RequestBody HotelDto hotelDto) {
        return hotelService.setActive(hotelDto);
    }

    @PutMapping("/block/hotel") // works
    ResponseEntity<?> block(@RequestBody HotelDto hotelDto) {
        return hotelService.block(hotelDto);
    }

    @PutMapping("/delete/hotel") // works
    ResponseEntity<?> delete(@RequestBody HotelDto hotelDto) {
        return hotelService.delete(hotelDto);
    }


    @GetMapping("/find/all/cities") // works
    ResponseEntity<?> findAllCities() {
        return cityService.findAll();
    }
}
