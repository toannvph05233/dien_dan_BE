package com.dien_dan.service;

import com.dien_dan.model.RoomChat;
import org.springframework.data.repository.query.Param;

public interface IRoomChatService {
    RoomChat getRoomChat(long id1, long id2);
    RoomChat getRoomChatById(long id);
    RoomChat save(RoomChat roomChat);
}
