package com.megacom.hotelreservationprojectmainmasterfinal.dao;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.City;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel, Long> {

    @Query(value = "select * from tb_hotel a where a.city_id = ?1", nativeQuery = true)
    List<Hotel> findHotelsByCityId(Long cityId); // required

    @Query(value = "select h from Hotel h where h.city = ?1 order by h.currentScore DESC")
    List<Hotel> findHotelsByRating(City city); // required

    @Query(value = "select * from tb_hotel h where h.city_id = :id and exists (select * from tb_room r where r.hotel_id = h.id and r.bed_type = :#{#bedType.name()})", nativeQuery = true)
    List<Hotel> findAllByCityAndBedType(@Param("id") Long cityId, @Param("bedType") EBedType bedType);
}
