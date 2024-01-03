package com.dien_dan.service;

import com.dien_dan.model.Chat;

import java.util.List;

public interface IChatService {
    List<Chat> getAllByRoomChatId(Long id);
    Chat save(Chat chat);
}
