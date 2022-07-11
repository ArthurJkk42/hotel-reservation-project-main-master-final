package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.PriceDao;
import com.megacom.hotelreservationprojectmainmasterfinal.dao.RoomCategoryDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.PriceMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.RoomCategoryMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PriceDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomCategoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.request.RoomCategoryAndPriceRequest;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PriceService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.RoomAndPriceService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomAndPriceServiceImpl implements RoomAndPriceService {

    @Autowired
    private RoomCategoryService roomCategoryService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private RoomCategoryDao roomCategoryDao;

    RoomCategoryMapper roomCategoryMapper = RoomCategoryMapper.INSTANCE;
    PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(RoomCategoryAndPriceRequest roomAndPriceRequest) {

        if (roomAndPriceRequest.getStartDate().equals(roomAndPriceRequest.getEndDate())
                || roomAndPriceRequest.getStartDate().after(roomAndPriceRequest.getEndDate())
                || roomAndPriceRequest.getEndDate().before(roomAndPriceRequest.getStartDate())) {
            return new ResponseEntity<>(Message.of("Invalid date input"), HttpStatus.NOT_ACCEPTABLE);
        } else {
            RoomCategoryDto roomCategory = new RoomCategoryDto();
            PriceDto price = new PriceDto();

            roomCategory.setRoomType(roomAndPriceRequest.getRoomType());

            RoomCategoryDto savedRoomCategory = roomCategoryService.save(roomCategory);

            price.setPrice(roomAndPriceRequest.getPrice());
            price.setStartDate(roomAndPriceRequest.getStartDate());
            price.setEndDate(roomAndPriceRequest.getEndDate());
            price.setRoomCategory(savedRoomCategory);

            PriceDto savedPrice = priceService.save(price);

            List<Object> list = new ArrayList<>();
            list.add(savedRoomCategory);
            list.add(savedPrice);

            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Override // optional (to be deleted maybe)
    public ResponseEntity<?> update(RoomCategoryAndPriceRequest roomCategoryAndPriceRequest) {
        return null;
    }
}
