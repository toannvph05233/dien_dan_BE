package com.dien_dan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "room_chat")
@AllArgsConstructor
@NoArgsConstructor
public class RoomChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private int count;
//    @ManyToMany(mappedBy = "roomChats", fetch = FetchType.LAZY)
//    private Set<Account> users = new HashSet<>();
}
