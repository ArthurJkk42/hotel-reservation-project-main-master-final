package com.megacom.hotelreservationprojectmainmasterfinal.models.request;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomCategoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.RoomCategory;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.ERoomType;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class RoomCategoryAndPriceRequest {
    private ERoomType roomType;

    private double price;
    private Date startDate;
    private Date endDate;
}
