package com.megacom.hotelreservationprojectmainmasterfinal.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String photos;
    private double currentScore;
}
