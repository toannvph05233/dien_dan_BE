package com.dien_dan.service.impl;

import com.dien_dan.model.Chat;
import com.dien_dan.repository.IChatRepo;
import com.dien_dan.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    IChatRepo iChatRepo;

    @Override
    public List<Chat> getAllByRoomChatId(Long id) {
        return iChatRepo.getAllByRoomChatId(id);
    }

    @Override
    public Chat save(Chat chat) {
        return iChatRepo.save(chat);
    }
}
