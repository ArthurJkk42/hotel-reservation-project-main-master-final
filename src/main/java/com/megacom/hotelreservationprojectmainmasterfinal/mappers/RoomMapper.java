package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto> {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
}
