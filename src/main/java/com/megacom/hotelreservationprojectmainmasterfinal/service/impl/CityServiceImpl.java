package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.CityDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.CityMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.CityDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.City;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    CityMapper cityMapper = CityMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(CityDto cityDto) {
        City savedCity = cityDao.save(cityMapper.toEntity(cityDto));
        return new ResponseEntity<>(cityMapper.toDto(savedCity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(CityDto cityDto) {
        boolean isExists = cityDao.existsById(cityDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("City not updated"), HttpStatus.NOT_FOUND);
        } else {
            City savedCity = cityDao.save(cityMapper.toEntity(cityDto));
            return new ResponseEntity<>(cityMapper.toDto(savedCity), HttpStatus.OK);
        }
    }

    @Override
    public CityDto findById(Long cityId) {
        City city = cityDao.findById(cityId).orElse(null);
        return cityMapper.toDto(city);
    }

    @Override
    public ResponseEntity<?> findFilteredByCity(Long cityId, Date checkInDate, Date checkOutDate, int guestCount, int roomCount) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<City> cityList = cityDao.findAllByName();
        return ResponseEntity.ok(cityMapper.toDtoList(cityList));
    }
}
