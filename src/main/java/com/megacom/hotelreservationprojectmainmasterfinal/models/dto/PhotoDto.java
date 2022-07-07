package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import lombok.Data;

@Data
public class PhotoDto {
    private Long id;
    private String photoLink;
    private int orderNum;
    private HotelDto hotel;
}
