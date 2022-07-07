package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewResponseDto {
    private Long id;
    private String text;
    private UserDto user;
    private Date reviewResponseDate;
}
