package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.UserDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
