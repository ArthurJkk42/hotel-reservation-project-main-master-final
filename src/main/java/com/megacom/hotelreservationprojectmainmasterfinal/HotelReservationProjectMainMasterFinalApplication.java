package com.megacom.hotelreservationprojectmainmasterfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients("com.megacom.hotelreservationprojectmainmasterfinal.microservices")
public class HotelReservationProjectMainMasterFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationProjectMainMasterFinalApplication.class, args);
	}

}
