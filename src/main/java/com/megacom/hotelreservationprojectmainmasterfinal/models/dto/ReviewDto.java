package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {
    private Long id;
    private UserDto user;
    private HotelDto hotel;
    private double score;
    private String text;
    private ReviewResponseDto reviewResponse;
    private Date reviewDate;
    private boolean isActive;
}
