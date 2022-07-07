package com.megacom.hotelreservationprojectmainmasterfinal.mappers;

import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.HotelDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.response.HotelResponse;
import com.megacom.hotelreservationprojectmainmasterfinal.service.PhotoService;
import com.megacom.hotelreservationprojectmainmasterfinal.service.impl.PhotoServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface HotelMapper extends BaseMapper<Hotel, HotelDto> {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    default HotelResponse hotelToHotelResponse(Hotel hotel) {
        if (hotel == null) {
            return null;
        }
        HotelResponse hotelResponse = new HotelResponse();

        hotelResponse.setId(hotel.getId());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setDescription(hotel.getDescription());
        hotelResponse.setAddress(hotel.getAddress());

        PhotoService photoService = new PhotoServiceImpl(); // процесс нахождения главного фото
        String photoLink = photoService.findMainPhoto(hotel); // наименьший порядковый номер фото найти

        hotelResponse.setPhotos(photoLink);

        hotelResponse.setCurrentScore(hotel.getCurrentScore());
        return hotelResponse;
    }

    default List<HotelResponse> convertToResponseList(List<Hotel> hotelList) {
        if (hotelList == null) {
            return null;
        }
        List<HotelResponse> hotelResponses = new ArrayList<HotelResponse>(hotelList.size());
        for (Hotel hotel : hotelList) {
            hotelResponses.add(hotelToHotelResponse(hotel));
        }
        return hotelResponses;
    }
}

