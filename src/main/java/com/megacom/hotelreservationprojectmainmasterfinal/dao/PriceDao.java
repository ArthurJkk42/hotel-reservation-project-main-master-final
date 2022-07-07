package com.megacom.hotelreservationprojectmainmasterfinal.dao;

import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceDao extends JpaRepository<Price, Long> {

    @Query(value = "select * from tb_price where p.category_id = :categoryId and p.startDate <= :now and p.endDate >= :now", nativeQuery = true)
    Price findPriceByRoomCategory(@Param("categoryId") Long categoryId, @Param("now") Date currentDate);
}
