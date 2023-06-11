package com.niklamix.graduateworkrest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends EntityDetail {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;
    @Column(name = "photo")
    private String photo;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "admin_flag")
    private boolean adminFlag;
    @Column(name = "enabled")
    private boolean enabled;
}
