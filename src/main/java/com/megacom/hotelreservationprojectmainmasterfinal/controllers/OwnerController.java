package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.*;
import com.megacom.hotelreservationprojectmainmasterfinal.models.request.RoomCategoryAndPriceRequest;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/owner")
public class OwnerController {

    @Autowired private HotelService hotelService;
    @Autowired private RoomAndPriceService roomAndPriceService;
    @Autowired private FileService fileService;
    @Autowired private ReviewResponseService reviewResponseService;
    @Autowired private CityService cityService;
    @Autowired private RoomCategoryService roomCategoryService;
    @Autowired private RoomService roomService;
    @Autowired private BookingService bookingService;
    @Autowired private BookingHistoryService bookingHistoryService;
    @Autowired private UserService userService;
    @Autowired private ReviewService reviewService;

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
        return cityService.update(cityDto);
    }

    @PostMapping("/save/hotel") // works
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

    @PostMapping("/save/room/category/and/price") // works
    public ResponseEntity<?> saveRoomCategoryAndPrice(@RequestBody RoomCategoryAndPriceRequest roomAndPriceRequest) {
        return roomAndPriceService.save(roomAndPriceRequest);
    }

    @PostMapping("/save/room") // works
    ResponseEntity<?> saveRoom(@RequestBody RoomDto roomDto) {
        return roomService.save(roomDto);
    }

    @PutMapping("/update/room") // works
    ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto) {
        return roomService.update(roomDto);
    }

    @PostMapping("/upload/image")
    ResponseEntity<?> uploadImageToHotel(@RequestParam MultipartFile file, @RequestParam Long hotelId, @RequestParam int orderNum) {
        return fileService.uploadImageToHotel(file, hotelId, orderNum);
    }

    @PostMapping("/respond/to/review")
    public ResponseEntity<?> respondToReview(@RequestParam Long reviewId, @RequestBody ReviewResponseDto reviewResponseDto) {
        return reviewResponseService.save(reviewId, reviewResponseDto);
    }

    @PutMapping("/edit/response/to/review")
    public ResponseEntity<?> editResponseReview(@RequestBody ReviewResponseDto reviewResponseDto) {
        return reviewResponseService.update(reviewResponseDto);
    }

    @DeleteMapping("/delete/review")
    ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.delete(reviewDto);
    }

    @GetMapping("/find/all/reviews")
    ResponseEntity<?> findAllReviews(@RequestParam Long hotelId) {
        List<ReviewDto> reviewDtos = reviewService.findAllByHotelAndActive(hotelId);
        if (reviewDtos.isEmpty()) {
            return new ResponseEntity<>(Message.of("reviews do not exist"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @PutMapping("/update/booking")
    public ResponseEntity<?> updateBook(@RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingDto);
    }

    @PutMapping("/cancel/booking")
    public ResponseEntity<?> cancelBooking(@RequestBody BookingDto bookingDto,@RequestParam String comment,@RequestParam Long userId) {
        return bookingService.cancel(bookingDto, comment, userId);
    }

    @DeleteMapping("/delete/booking")
    public ResponseEntity<?> deleteBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.delete(bookingDto);
    }
}
