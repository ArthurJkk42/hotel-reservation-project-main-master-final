package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewResponseService {

    ResponseEntity<?> save(Long reviewId, ReviewResponseDto reviewResponseDto);

    ReviewResponseDto findById(Long id);

    ResponseEntity<?> update(ReviewResponseDto reviewResponseDto);

    ResponseEntity<?> delete(ReviewResponseDto reviewResponseDto);
}
