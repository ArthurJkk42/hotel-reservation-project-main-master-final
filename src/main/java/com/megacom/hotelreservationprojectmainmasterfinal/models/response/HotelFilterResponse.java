package com.megacom.hotelreservationprojectmainmasterfinal.models.response;

import lombok.Data;

import java.util.List;

@Data
public class HotelFilterResponse {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private double currentScore;
    private List<RoomFilterResponse> availableRooms;
}
