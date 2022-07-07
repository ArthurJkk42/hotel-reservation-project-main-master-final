package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/findHotelsByCityAndRating")
    ResponseEntity<?> findHotelsByCityAndRating(@RequestParam Long cityId) {
        return hotelService.findHotelsByCityIdAndRating(cityId);
    }

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

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @PostMapping("/updateBooking")
    public ResponseEntity<?> updateBook(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @PutMapping("/cancelBooking")
    public ResponseEntity<?> cancelBooking(@RequestBody BookingDto bookingDto,@RequestParam String comment,@RequestParam Long userId) {
        return bookingService.cancel(bookingDto, comment, userId);
    }

    @PostMapping("/review")
    ResponseEntity<?> reviewDto(@RequestBody ReviewDto reviewDto) {
         return reviewService.save(reviewDto);
    }

    @PutMapping("/updateReview")
    ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.update(reviewDto);
    }

    @PutMapping("/deleteReview")
    ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.delete(reviewDto);
    }

}
