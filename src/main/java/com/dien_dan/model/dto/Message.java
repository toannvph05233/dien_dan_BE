package com.dien_dan.model.dto;

import com.dien_dan.model.RoomChat;
import lombok.Data;

@Data
public class Message {
    private String message;
    private int idFriend;
    private int idUser;
    private RoomChat room;
}
