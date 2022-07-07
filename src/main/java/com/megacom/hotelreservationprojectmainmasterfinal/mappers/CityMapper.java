package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.CityDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends BaseMapper<City, CityDto> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
