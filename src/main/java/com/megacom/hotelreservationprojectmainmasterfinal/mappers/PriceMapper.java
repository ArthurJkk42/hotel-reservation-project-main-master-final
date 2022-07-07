package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.PriceDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto> {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
}
