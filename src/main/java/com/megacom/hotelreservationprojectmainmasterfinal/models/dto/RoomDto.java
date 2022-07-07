package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.RoomCategory;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.ERoomType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EView;
import lombok.Data;

@Data
public class RoomDto {
    private Long id;
    private int capacity;
    private EBedType bedType;
    private String square;
    private boolean wifi;
    private EView view;
    private HotelDto hotel;
    private RoomCategoryDto roomCategory;
}
