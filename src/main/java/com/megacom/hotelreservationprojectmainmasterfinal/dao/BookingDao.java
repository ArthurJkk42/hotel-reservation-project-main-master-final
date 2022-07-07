package com.megacom.hotelreservationprojectmainmasterfinal.dao;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Booking;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Room;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

    List<Booking> findAllByRoomAndStatus(Room room, EBookingStatus status);
}
