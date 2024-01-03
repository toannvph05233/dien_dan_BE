package com.dien_dan.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //    vĩ độ
    private double latitude;
    //    kinh độ
    private double longitude;
    @ManyToOne
    private Account account;
}
