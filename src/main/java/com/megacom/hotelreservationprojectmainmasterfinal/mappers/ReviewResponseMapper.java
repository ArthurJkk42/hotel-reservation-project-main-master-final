package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.ReviewResponseDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewResponseMapper extends BaseMapper<ReviewResponse, ReviewResponseDto> {
    ReviewResponseMapper INSTANCE = Mappers.getMapper(ReviewResponseMapper.class);
}
