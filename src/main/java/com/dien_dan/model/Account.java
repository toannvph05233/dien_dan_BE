package com.dien_dan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String avatar;
    private String status;
    private String gender;
    private Date birthday;
    private String bio;
    @ManyToOne
    private Area area;
    @ManyToOne
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<RoomChat> roomChats = new HashSet<>();


    public Account(long id, String avatar, String bio, String password, String username, Role role, String status, String gender, Area area) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.status = status;
        this.gender = gender;
        this.bio = bio;
        this.area = area;
        this.role = role;
    }

    public Account(long id) {
        this.id = id;
    }
}
