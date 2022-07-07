package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.PriceDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.PriceMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PriceDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Price;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDao priceDao;

    private PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.toEntity(priceDto);
        Price priceSaved = priceDao.save(price);
        return priceMapper.toDto(priceSaved);
    }

    @Override
    public PriceDto findById(Long id) {
        Price price = priceDao.findById(id).orElse(null);
        return priceMapper.toDto(price);
    }

    @Override
    public PriceDto findByRoomId(Long id) {
        return null;
    }

    @Override
    public PriceDto findByGuestId(Long id) {
        return null;
    }

    @Override
    public PriceDto update(PriceDto priceDto) {
        boolean isExists = priceDao.existsById(priceDto.getId());
        if (!isExists) {
            return null;
        } else {
            Price price = priceMapper.toEntity(priceDto);
            Price updatedPrice = priceDao.save(price);
            return priceMapper.toDto(price);
        }
    }

    @Override
    public PriceDto delete(PriceDto priceDto) {
        return null;
    }

    @Override
    public PriceDto findPriceByRoom(Long categoryId) {
        Price price = priceDao.findPriceByRoomCategory(categoryId, new Date());
        return priceMapper.toDto(price);
    }

    @Override
    public List<PriceDto> findPricesByHotel(Long id) {
        return null;
    }

    @Override
    public List<PriceDto> findPricesByCity() {
        return null;
    }
}
