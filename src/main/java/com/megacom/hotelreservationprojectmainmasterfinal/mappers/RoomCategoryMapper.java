package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomCategoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.RoomCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomCategoryMapper extends BaseMapper<RoomCategory, RoomCategoryDto> {
    RoomCategoryMapper INSTANCE = Mappers.getMapper(RoomCategoryMapper.class);
}
