package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.User;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EHotelStatus;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private EHotelStatus hotelStatus;
    private CityDto city;
    private double currentScore;
    private UserDto manager;
}
