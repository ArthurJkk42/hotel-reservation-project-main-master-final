package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper extends BaseMapper<Booking, BookingDto> {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
}
