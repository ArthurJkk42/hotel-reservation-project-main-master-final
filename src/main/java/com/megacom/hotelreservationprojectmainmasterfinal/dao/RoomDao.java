package com.megacom.hotelreservationprojectmainmasterfinal.dao;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Hotel;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Room;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {

    @Query(value = "select * from tb_room a where a.hotel_id = ?1", nativeQuery = true)
    List<Room> findRoomsByHotel(Long hotelId);

    @Query("select r from Room r where r.hotel = ?1 and r.bedType = ?2 and r.capacity >= ?3")
    List<Room> findAllByActiveTrueAndHotelAndBedTypeAndCapacity(Hotel hotel, EBedType bedType, int capacity);


}
