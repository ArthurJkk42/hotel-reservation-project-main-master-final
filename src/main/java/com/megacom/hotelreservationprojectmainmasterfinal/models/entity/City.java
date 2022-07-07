package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_city")
public class City {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean isActive;
}
