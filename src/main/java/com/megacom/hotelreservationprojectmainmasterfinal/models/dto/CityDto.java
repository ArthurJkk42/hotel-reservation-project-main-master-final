package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import lombok.Data;

@Data
public class CityDto {
    private Long id;
    private String name;
    private boolean isActive;
}
