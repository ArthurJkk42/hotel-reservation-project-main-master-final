package com.megacom.hotelreservationprojectmainmasterfinal.models.request;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.ERoomType;
import lombok.Data;

import java.util.Date;

@Data
public class RoomCategoryAndPriceRequestWithId {
    private Long roomCategoryId;
    private ERoomType roomType;

    private double price;
    private Date startDate;
    private Date endDate;
}
