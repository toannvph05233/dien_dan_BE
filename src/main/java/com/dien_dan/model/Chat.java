package com.dien_dan.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private LocalDateTime dateTime;
    @ManyToOne
    private Account account;
    @ManyToOne
    private RoomChat roomChat;

}
