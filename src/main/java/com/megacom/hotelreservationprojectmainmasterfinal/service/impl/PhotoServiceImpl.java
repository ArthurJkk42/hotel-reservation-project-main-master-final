package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.PhotoDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.HotelMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.PhotoMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PhotoDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Photo;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;


    @Override
    public PhotoDto save(PhotoDto photoDto) {
        Photo photo = photoMapper.toEntity(photoDto);
        Photo savedPhoto = photoDao.save(photo);
        return photoMapper.toDto(savedPhoto);
    }

    @Override
    public PhotoDto findById(Long id) {
        Photo photo = photoDao.findById(id).orElse(null);
        return photoMapper.toDto(photo);
    }

    @Override
    public List<PhotoDto> findAllPhotosByHotel(Hotel hotel) {
        List<Photo> photoList = photoDao.findAllByHotelId(hotel.getId());
        return photoMapper.toDtoList(photoList);
    }

    @Override
    public String findMainPhoto(Hotel hotel) {
        List<PhotoDto> photoListDto = findAllPhotosByHotel(hotel);
        List<Photo> photoList = photoMapper.toEntityList(photoListDto);

        photoList.stream().forEach(x -> {
            List<Integer> orderNumList = new ArrayList<>();
            for (int i : orderNumList) {
                orderNumList.add(x.getOrderNum());
            }
            int mainPhotoNum = Collections.min(orderNumList);
            if (x.getOrderNum() != mainPhotoNum) {
                photoList.remove(x);
            }
        });
        String mainPhotoLink = photoList.stream().findFirst().get().getPhotoLink();
        return mainPhotoLink;
    }
}
