package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {
    private Long id;
    private HotelDto hotel;
    private RoomDto room;
    private Date checkInDate;
    private Date checkOutDate;
    private UserDto user;
    private String comment;
    private EBookingStatus status;
    private double sum;
}
