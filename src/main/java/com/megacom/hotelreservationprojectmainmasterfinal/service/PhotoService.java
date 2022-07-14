package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PhotoDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;

import java.util.List;

public interface PhotoService {

    PhotoDto save(PhotoDto photoDto);

    PhotoDto findById(Long id);

    String findMainPhoto(Hotel hotel);

    List<PhotoDto> findAllPhotosByHotel(Hotel hotel);
}
