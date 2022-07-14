package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PhotoDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelFilterResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PhotoService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired private BookingService bookingService;
    @Autowired private HotelService hotelService;
    @Autowired private ReviewService reviewService;
    @Autowired private PhotoService photoService;

    @GetMapping("/filterByCity")
    ResponseEntity<?> filterByCity(@RequestParam Long cityId,
                                   @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkInDate,
                                   @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOutDate,
                                   @RequestParam int guestCount,
                                   @RequestParam EBedType bedType) {
        return hotelService.filterByCity(cityId, checkInDate, checkOutDate, guestCount, bedType);
    }

    @PostMapping("/book") // works
    ResponseEntity<?> book(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @PutMapping("/update/booking") // works
    ResponseEntity<?> updateBook(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @PutMapping("/cancel/booking") // works
    ResponseEntity<?> cancelBooking(@RequestBody BookingDto bookingDto,@RequestParam String comment, @RequestParam Long userId) {
        return bookingService.cancel(bookingDto, comment, userId);
    }

    @PostMapping("/review") // works
    ResponseEntity<?> reviewDto(@RequestBody ReviewDto reviewDto) {
         return reviewService.save(reviewDto);
    }

    @PutMapping("/update/review") // works
    ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.update(reviewDto);
    }

    @DeleteMapping("/delete/review") // works
    ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.delete(reviewDto);
    }

}
