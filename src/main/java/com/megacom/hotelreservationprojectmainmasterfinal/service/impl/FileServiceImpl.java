package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.mappers.HotelMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.microservices.FileServiceFeign;
import com.megacom.hotelreservationprojectmainmasterfinal.microservices.json.FileServiceResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PhotoDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.Message;
import com.megacom.hotelreservationprojectmainmasterfinal.service.FileService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileServiceFeign fileServiceFeign;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private PhotoService photoService;

    HotelMapper hotelMapper = HotelMapper.INSTANCE;

    @Override
    public ResponseEntity<?> uploadImageToHotel(MultipartFile file, Long hotelId, int orderNum) {
        HotelDto hotelDto = hotelService.findById(hotelId);
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        if (hotelDto != null) {
            PhotoDto photoDto = new PhotoDto();
            photoDto.setHotel(hotelDto);
            photoDto.setOrderNum(orderNum);
            try {
                FileServiceResponse response = fileServiceFeign.upload(file);
                photoDto.setPhotoLink(response.getDownloadUri());
                PhotoDto saved = photoService.save(photoDto);
                return ResponseEntity.ok(saved);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(Message.of("Не удалось сохранить фото"), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(Message.of("Не удалось найти объект отеля"), HttpStatus.NOT_ACCEPTABLE);
    }
}
