package com.megacom.hotelreservationprojectmainmasterfinal.models.entity;

import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EUserStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EUserType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private EUserType userType;
    @Enumerated(value = EnumType.STRING)
    private EUserStatus userStatus;
}
