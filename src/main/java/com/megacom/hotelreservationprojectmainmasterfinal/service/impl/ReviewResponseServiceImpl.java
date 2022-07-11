package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.ReviewDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.ReviewResponseDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.ReviewResponseMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewResponseDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Review;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.ReviewResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.ReviewResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewResponseServiceImpl implements ReviewResponseService {

    @Autowired
    private ReviewResponseDao reviewResponseDao;
    @Autowired
    private ReviewDao reviewDao;

    ReviewResponseMapper reviewResponseMapper = ReviewResponseMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(Long reviewId, ReviewResponseDto reviewResponseDto) {
        Review review = reviewDao.findById(reviewId).orElse(null);
        if (review == null) {
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        } else {
            ReviewResponse reviewResponse = reviewResponseMapper.toEntity(reviewResponseDto);

            reviewResponse.setReviewResponseDate(new Date());

            ReviewResponse savedReviewResponse = reviewResponseDao.save(reviewResponse);
            review.setReviewResponse(savedReviewResponse);
            Review savedReview = reviewDao.save(review);
            return new ResponseEntity<>(savedReviewResponse, HttpStatus.OK);
        }
    }

    @Override
    public ReviewResponseDto findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ReviewResponseDto reviewResponseDto) {
        boolean isExists = reviewResponseDao.existsById(reviewResponseDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Respond not found"), HttpStatus.NOT_FOUND);
        } else {
            ReviewResponse reviewResponse = reviewResponseMapper.toEntity(reviewResponseDto);

            reviewResponse.setReviewResponseDate(new Date());

            ReviewResponse editedReviewResponse = reviewResponseDao.save(reviewResponse);
            return new ResponseEntity<>(editedReviewResponse, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewResponseDto reviewResponseDto) {
        boolean isExists = reviewResponseDao.existsById(reviewResponseDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Respond not found"), HttpStatus.NOT_FOUND);
        } else {
            ReviewResponse replyToReview = reviewResponseMapper.toEntity(reviewResponseDto);
            reviewResponseDao.deleteById(replyToReview.getId());
            return new ResponseEntity<>(Message.of("ReviewResponse deleted"), HttpStatus.OK);
        }
    }


}
