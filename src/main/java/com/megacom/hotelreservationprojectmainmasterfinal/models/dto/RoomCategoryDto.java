package com.megacom.hotelreservationprojectmainmasterfinal.models.dto;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.ERoomType;
import lombok.Data;

@Data
public class RoomCategoryDto {
    private Long id;
    private ERoomType roomType;
}
