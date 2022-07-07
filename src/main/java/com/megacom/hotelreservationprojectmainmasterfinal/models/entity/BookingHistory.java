package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EBookingStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_booking_history")
public class BookingHistory {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    private Date changeDate;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date checkInDate;
    private Date checkOutDate;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;
    @Enumerated(value = EnumType.STRING)
    private EBookingStatus status;
    private double sum;
}
