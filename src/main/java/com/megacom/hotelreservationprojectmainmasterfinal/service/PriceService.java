package com.megacom.hotelreservationprojectmainmasterfinal.service;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PriceDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Room;

import java.util.List;

public interface PriceService {

    PriceDto save(PriceDto priceDto);

    PriceDto findById(Long id);

    PriceDto findByRoomId(Long id);

    PriceDto findByGuestId(Long id);

    PriceDto update(PriceDto priceDto);

    PriceDto delete(PriceDto priceDto);

    PriceDto findPriceByRoom(Long categoryId);

    List<PriceDto> findPricesByHotel(Long id);

    List<PriceDto> findPricesByCity();
}
