package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBedType;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_room")
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private int capacity;
    @Enumerated(value = EnumType.STRING)
    private EBedType bedType;
    private String square;
    private boolean wifi;
    @Enumerated(value = EnumType.STRING)
    private EView view;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "room_category_id")
    private RoomCategory roomCategory;
}
