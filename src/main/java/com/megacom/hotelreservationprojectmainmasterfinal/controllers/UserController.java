package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelFilterResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.service.BookingService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
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

    @GetMapping("/filterByRating")
    ResponseEntity<?> filterByRating(@RequestBody List<HotelFilterResponse> filteredHotels,
                                     @RequestParam double rating) {
        return hotelService.filterByRating(filteredHotels, rating);
    }

    @PostMapping("/book")
    ResponseEntity<?> book(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @PutMapping("/update/booking")
    ResponseEntity<?> updateBook(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @PutMapping("/cancel/booking")
    ResponseEntity<?> cancelBooking(@RequestBody BookingDto bookingDto,@RequestParam String comment,@RequestParam Long userId) {
        return bookingService.cancel(bookingDto, comment, userId);
    }

    @DeleteMapping("/delete/booking")
    ResponseEntity<?> deleteBook(@RequestBody BookingDto bookingDto) {
        return bookingService.delete(bookingDto);
    }

    @PostMapping("/review")
    ResponseEntity<?> reviewDto(@RequestBody ReviewDto reviewDto) {
         return reviewService.save(reviewDto);
    }

    @PutMapping("/update/review")
    ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.update(reviewDto);
    }

    @DeleteMapping("/delete/review")
    ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.delete(reviewDto);
    }
}
