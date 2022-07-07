package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PhotoDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper extends BaseMapper<Photo, PhotoDto> {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
}
