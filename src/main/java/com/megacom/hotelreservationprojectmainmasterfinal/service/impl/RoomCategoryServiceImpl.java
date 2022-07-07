package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.RoomCategoryDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.BookingHistoryMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.RoomCategoryMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomCategoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.RoomCategory;
import com.megacom.hotelreservationprojectmainmasterfinal.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {

    @Autowired
    private RoomCategoryDao roomCategoryDao;

    private RoomCategoryMapper roomCategoryMapper = RoomCategoryMapper.INSTANCE;

    @Override
    public RoomCategoryDto save(RoomCategoryDto roomCategoryDto) {
        RoomCategory roomCategory = roomCategoryMapper.toEntity(roomCategoryDto);
        RoomCategory savedRoomCategory = roomCategoryDao.save(roomCategory);
        return roomCategoryMapper.toDto(savedRoomCategory);
    }

    @Override
    public RoomCategoryDto update(RoomCategoryDto roomCategoryDto) {
        boolean isExists = roomCategoryDao.existsById(roomCategoryDto.getId());
        if (!isExists) {
            throw new NoSuchElementException("Room category you've typed does not exist!");
        } else {
            RoomCategory roomCategory = roomCategoryMapper.toEntity(roomCategoryDto);
            RoomCategory updatedRoomCategory = roomCategoryDao.save(roomCategory);
            return roomCategoryMapper.toDto(updatedRoomCategory);
        }
    }
}
