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

    HotelMapper hotelMapper = HotelMapper.INSTANCE;
    ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);

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
            Review review = reviewMapper.toEntity(reviewDto);

            review.setReviewDate(new Date());

            Review updatedReview = reviewDao.save(review);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(false);
        ResponseEntity<?> deletedReview = update(reviewMapper.toDto(review));
        if (deletedReview.getStatusCode().equals(HttpStatus.OK)) {
            return new ResponseEntity<>(deletedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Message.of("Review not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ReviewDto findById(Long id) {
        Review review = reviewDao.findById(id).orElse(null);
        return reviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> findAllByHotelAndActive(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return reviewMapper.toDtoList(reviewDao.findAllByActiveTrueAndHotel(hotel));
    }
}

