package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.RoomDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.RoomMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Room;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    RoomMapper roomMapper = RoomMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        Room savedRoom = roomDao.save(room);
        return new ResponseEntity<>(roomMapper.toDto(savedRoom), HttpStatus.OK);
    }

    @Override
    public RoomDto findById(Long id) {
        Room room = roomDao.findById(id).orElse(null);
        return roomMapper.toDto(room);
    }

    @Override
    public ResponseEntity<?> update(RoomDto roomDto) {
        boolean isExists = roomDao.existsById(roomDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("Room not found"), HttpStatus.NOT_FOUND);
        } else {
            Room room = roomMapper.toEntity(roomDto);
            Room updatedRoom = roomDao.save(room);
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        }
    }



    @Override
    public List<RoomDto> filterByBedType(EBedType eBedType) {
        return null;
    }

    @Override
    public List<RoomDto> findAllRoomsByHotel(Hotel hotel, EBedType bedType, int capacity) {
        List<Room> roomList = roomDao.findAllByActiveTrueAndHotelAndBedTypeAndCapacity(hotel, bedType, capacity);
        return roomMapper.toDtoList(roomList);
    }

    @Override
    public List<RoomDto> findByBooking(Long id) {
        return null;
    }

    @Override
    public List<RoomDto> findRoomsByHotel(Long id) {
        List<Room> roomList = roomDao.findRoomsByHotel(id);
        return roomMapper.toDtoList(roomList);
    }

    @Override
    public List<RoomDto> findByHotelGuestId(Long id) {
        return null;
    }
}
