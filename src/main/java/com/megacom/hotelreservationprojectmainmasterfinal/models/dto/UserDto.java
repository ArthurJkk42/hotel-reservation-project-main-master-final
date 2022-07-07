package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EUserStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EUserType;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private EUserType userType;
    private EUserStatus userStatus;
}
