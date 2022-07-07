package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookingHistoryDto {
    private Long id;
    private BookingDto booking;
    private Date changeDate;
    private String comment;
    private UserDto user;
    private Date checkInDate;
    private Date checkOutDate;
    private UserDto guest;
    private EBookingStatus status;
    private double sum;
}
