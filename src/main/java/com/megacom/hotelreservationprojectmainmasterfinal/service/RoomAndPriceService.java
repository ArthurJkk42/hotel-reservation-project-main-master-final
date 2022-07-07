package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.request.RoomCategoryAndPriceRequest;
import org.springframework.http.ResponseEntity;

public interface RoomAndPriceService {

    ResponseEntity<?> save(RoomCategoryAndPriceRequest roomAndPriceRequest);

    ResponseEntity<?> update(RoomCategoryAndPriceRequest roomCategoryAndPriceRequest);

}
