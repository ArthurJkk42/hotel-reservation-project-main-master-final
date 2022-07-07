package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.BookingHistoryDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.BookingHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingHistoryMapper extends BaseMapper<BookingHistory, BookingHistoryDto> {
    BookingHistoryMapper INSTANCE = Mappers.getMapper(BookingHistoryMapper.class);
}
