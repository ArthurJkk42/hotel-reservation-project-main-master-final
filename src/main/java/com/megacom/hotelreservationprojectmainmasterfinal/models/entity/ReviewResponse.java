package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_review_response")
public class ReviewResponse {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date reviewResponseDate;
}
