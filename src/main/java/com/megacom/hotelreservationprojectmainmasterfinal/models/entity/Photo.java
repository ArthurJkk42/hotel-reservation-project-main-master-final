package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_photo")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    private String photoLink;
    private int orderNum;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
