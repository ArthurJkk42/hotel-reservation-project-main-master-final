package com.megacom.hotelreservationprojectmainmasterfinal.scheduler;

import com.megacom.hotelreservationprojectmainmasterfinal.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RatingCountScheduler {

    @Autowired
    HotelService hotelService;

    @Scheduled(cron = "0 55 23 * * *")
    public void scheduledFixedDelayTask() {
        hotelService.countCurrentScore();
    }
}
