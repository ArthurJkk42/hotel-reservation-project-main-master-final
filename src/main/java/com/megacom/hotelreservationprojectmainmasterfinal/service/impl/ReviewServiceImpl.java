package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.HotelDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.ReviewDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.HotelMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.ReviewMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Review;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelService hotelService;

    HotelMapper hotelMapper = HotelMapper.INSTANCE;
    ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        if (reviewDto.getScore() > 5 || reviewDto.getScore() < 0) {
            return new ResponseEntity<>(Message.of("Invalid score input"), HttpStatus.NOT_ACCEPTABLE);
        }
        Review review = reviewMapper.toEntity(reviewDto);

        double score = review.getScore();
        if (score % 1 == 0) {
            review.setScore(score);
        } else {
            double finalScore = Math.round((score)*10)/10;
            review.setScore(finalScore);
        }

        review.setReviewDate(new Date());

        Review savedReview = reviewDao.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        } else {
            if (reviewDto.getScore() > 5 || reviewDto.getScore() < 0) {
                return new ResponseEntity<>(Message.of("Invalid score input"), HttpStatus.NOT_ACCEPTABLE);
            }
            Review review = reviewMapper.toEntity(reviewDto);

            review.setReviewDate(new Date());

            Review updatedReview = reviewDao.save(review);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        } else {
            Review review = reviewMapper.toEntity(reviewDto);

            review.setReviewDate(new Date());
            review.setActive(false);

            Review updatedReview = reviewDao.save(review);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
    }

    @Override
    public ReviewDto findById(Long id) {
        Review review = reviewDao.findById(id).orElse(null);
        return reviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> findAllByHotelAndActive(Long hotelId) {
        HotelDto hotelDto = hotelService.findById(hotelId);
        return reviewMapper.toDtoList(reviewDao.findAllByIsActiveTrueAndHotel(hotelMapper.toEntity(hotelDto)));
    }
}

