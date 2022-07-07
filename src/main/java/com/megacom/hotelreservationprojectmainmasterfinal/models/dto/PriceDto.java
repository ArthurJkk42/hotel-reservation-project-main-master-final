package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PriceDto {
    private Long id;
    private double price;
    private Date startDate;
    private Date endDate;
    private RoomCategoryDto roomCategory;
}
