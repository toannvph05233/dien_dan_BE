package com.dien_dan.model.dto;

import com.dien_dan.model.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultSocket {
    private int idFriend;
    private int idUser;
    private Chat chat;
    private String type;

}
