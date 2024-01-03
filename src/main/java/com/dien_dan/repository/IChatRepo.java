package com.dien_dan.repository;

import com.dien_dan.model.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IChatRepo extends CrudRepository<Chat, Long> {
    List<Chat> getAllByRoomChatId(Long id);
}
