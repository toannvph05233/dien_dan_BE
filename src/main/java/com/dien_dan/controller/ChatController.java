package com.dien_dan.controller;

import com.dien_dan.model.Account;
import com.dien_dan.model.Chat;
import com.dien_dan.model.RoomChat;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.IChatService;
import com.dien_dan.service.IRoomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("chats")
public class ChatController {
    @Autowired
    IRoomChatService roomChatService;

    @Autowired
    IChatService chatService;

    @Autowired
    IAccountService iAccountService;

    @GetMapping("room/{idUser1}/{idUser2}")
    public RoomChat getRoomChat(@PathVariable int idUser1, @PathVariable int idUser2){
        if (idUser2 != 10) {
            RoomChat roomChat = roomChatService.getRoomChat(idUser1, idUser2);
            if (roomChat == null) {
                RoomChat newRoom = new RoomChat();
                newRoom.setCount(2);
                newRoom.setType("single");
                roomChatService.save(newRoom);
                Account account1 = iAccountService.findById(idUser1);
                Account account2 = iAccountService.findById(idUser2);
                Set<RoomChat> roomChats1 = account1.getRoomChats();
                Set<RoomChat> roomChats2 = account2.getRoomChats();
                roomChats1.add(newRoom);
                roomChats2.add(newRoom);
                account1.setRoomChats(roomChats1);
                account2.setRoomChats(roomChats2);
                iAccountService.save(account1);
                iAccountService.save(account2);
                return newRoom;
            }
            return roomChat;
        }
        return null;
    }

    @GetMapping("{roomId}")
    public List<Chat> getChat(@PathVariable long roomId){
        return chatService.getAllByRoomChatId(roomId);
    }
}
