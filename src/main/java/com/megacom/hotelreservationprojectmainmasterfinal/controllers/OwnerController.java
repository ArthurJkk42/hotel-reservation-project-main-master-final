package com.megacom.hotelreservationprojectmainmasterfinal.controllers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.*;
import com.megacom.hotelreservationprojectmainmasterfinal.models.request.RoomCategoryAndPriceRequest;
import com.megacom.hotelreservationprojectmainmasterfinal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/owner")
public class OwnerController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomAndPriceService roomAndPriceService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ReviewResponseService reviewResponseService;
    @Autowired
    private CityService cityService;
    @Autowired
    private RoomCategoryService roomCategoryService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingHistoryService bookingHistoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/saveCity") // works
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto) {
        return cityService.save(cityDto);
    }

    @PutMapping("/updateCity") // works
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto) {
        return cityService.update(cityDto);
    }

    @PostMapping("/saveHotel") // works
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.save(hotelDto);
    }

    @PutMapping("/updateHotel") // works
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.update(hotelDto);
    }

    @PostMapping("/saveRoomCategoryAndPrice") // works
    public ResponseEntity<?> saveRoomCategoryAndPrice(@RequestBody RoomCategoryAndPriceRequest roomAndPriceRequest) {
        return roomAndPriceService.save(roomAndPriceRequest);
    }

//    @PutMapping("/updateRoomCategoryAndPrice") // to be removed maybe (not finished)
//    public ResponseEntity<?> updateRoomCategoryAndPrice(@RequestBody RoomCategoryAndPriceRequest roomAndPriceRequest) {
//        return roomAndPriceService.update(roomAndPriceRequest);
//    }

    @PostMapping("/saveRoom") // works
    ResponseEntity<?> saveRoom(@RequestBody RoomDto roomDto) {
        return roomService.save(roomDto);
    }

    @PutMapping("/updateRoom") // works
    ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto) {
        return roomService.update(roomDto);
    }

    @PostMapping("/uploadImage")
    ResponseEntity<?> uploadImageToHotel(@RequestParam MultipartFile file, @RequestParam Long hotelId, @RequestParam int orderNum) {
        return fileService.uploadImageToHotel(file, hotelId, orderNum);
    }

    @PostMapping("/respondToReview")
    public ResponseEntity<?> respondToReview(@RequestParam Long reviewId, @RequestBody ReviewResponseDto reviewResponseDto) {
        return reviewResponseService.save(reviewId, reviewResponseDto);
    }

    @PutMapping("/editResponseReview")
    public ResponseEntity<?> editResponseReview(@RequestBody ReviewResponseDto reviewResponseDto) {
        return reviewResponseService.update(reviewResponseDto);
    }

    @PutMapping("/deleteReview")
    ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.delete(reviewDto);
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

    @PutMapping("/deleteBooking")
    public ResponseEntity<?> deleteBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.delete(bookingDto);
    }
}
