package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomCategoryDto;

public interface RoomCategoryService {

    RoomCategoryDto save(RoomCategoryDto roomCategoryDto);

    RoomCategoryDto update(RoomCategoryDto roomCategoryDto);
}
