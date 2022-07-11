package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<?> save(ReviewDto reviewDto);

    ResponseEntity<?> update(ReviewDto reviewDto);

    ResponseEntity<?> delete(ReviewDto reviewDto);

    ReviewDto findById(Long id);

    List<ReviewDto> findAllByHotelAndActive(Long hotelId);
}
