package com.dien_dan.service.impl;

import com.dien_dan.model.RoomChat;
import com.dien_dan.repository.IRoomChatRepo;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.IRoomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomChatServiceImpl implements IRoomChatService {
    @Autowired
    IRoomChatRepo iRoomChatRepo;

    @Override
    public RoomChat getRoomChat(long id1, long id2) {
        return iRoomChatRepo.getRoomChat(id1,id2);
    }

    @Override
    public RoomChat getRoomChatById(long id) {
        return iRoomChatRepo.findById(id).get();
    }

    @Override
    public RoomChat save(RoomChat roomChat) {
        return iRoomChatRepo.save(roomChat);
    }
}
