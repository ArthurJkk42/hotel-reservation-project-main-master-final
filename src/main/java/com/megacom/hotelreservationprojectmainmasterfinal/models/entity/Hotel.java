package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EHotelStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private EHotelStatus hotelStatus;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private double currentScore;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;
}
