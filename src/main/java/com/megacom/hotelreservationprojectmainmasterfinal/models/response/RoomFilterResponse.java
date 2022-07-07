package com.megacom.hotelreservationprojectmainmasterfinal.models.response;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EView;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RoomFilterResponse {

    private Long id;
    private int capacity;
    private EBedType bedType;
    private String square;
    private boolean wifi;
    private EView view;
    private Date checkInDate;
    private Date checkOutDate;
    private double price;
}
