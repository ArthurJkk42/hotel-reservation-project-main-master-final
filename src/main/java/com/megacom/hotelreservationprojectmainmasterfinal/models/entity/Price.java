package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_price")
public class Price {
    @Id
    @GeneratedValue
    private Long id;
    private double price;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "room_category_id")
    private RoomCategory roomCategory;
}
