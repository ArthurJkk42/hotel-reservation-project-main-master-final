package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/setActive")
    ResponseEntity<?> setActive(@RequestBody HotelDto hotelDto) {
        return hotelService.setActive(hotelDto);
    }

    @PostMapping("/deleteHotel")
    ResponseEntity<?> delete(@RequestBody HotelDto hotelDto) {
        return hotelService.delete(hotelDto);
    }

    @PostMapping("/blockHotel")
    ResponseEntity<?> block(@RequestBody HotelDto hotelDto) {
        return hotelService.block(hotelDto);
    }
}
