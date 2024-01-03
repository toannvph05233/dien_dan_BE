package com.dien_dan.controller;

import com.dien_dan.model.Account;
import com.dien_dan.model.Chat;
import com.dien_dan.model.Notification;
import com.dien_dan.model.RoomChat;
import com.dien_dan.model.dto.Message;
import com.dien_dan.model.dto.ResultSocket;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.IChatService;
import com.dien_dan.service.INotificationService;
import com.dien_dan.service.IRoomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebsocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    IChatService chatService;
    @Autowired
    IAccountService accountService;
    @Autowired
    IRoomChatService roomChatService;
    @Autowired
    INotificationService notificationService;

    @MessageMapping("/chat/single")
    public void single(Message message) throws Exception {
        Account account = accountService.findById(message.getIdUser());
        RoomChat roomChat = roomChatService.getRoomChatById(message.getRoom().getId());
        Chat chat = new Chat();
        chat.setAccount(account);
        chat.setRoomChat(roomChat);
        chat.setContent(message.getMessage());
        chat.setDateTime(LocalDateTime.now());
        chatService.save(chat);
        ResultSocket resultSocket = new ResultSocket(message.getIdFriend(), message.getIdUser(), chat, "chat");
        Notification notification = new Notification(0, "Bạn có tin nhắn mới", false, new Account(message.getIdUser()), message.getIdFriend());
        notificationService.save(notification);
        simpMessagingTemplate.convertAndSend("/topic/" + message.getIdUser(), resultSocket);
        simpMessagingTemplate.convertAndSend("/topic/" + message.getIdFriend(), resultSocket);
    }

    @MessageMapping("/isOnline")
    public void online(Message message) {
        List<Account> accountsOnline = accountService.getAccountByStatus("online", message.getIdUser());
        ResultSocket resultSocket = new ResultSocket(message.getIdFriend(), message.getIdUser(), null, "online");
        for (Account a : accountsOnline) {
            simpMessagingTemplate.convertAndSend("/topic/" + a.getId(), resultSocket);

        }
    }

    @MessageMapping("/chat/all")
    public void group(Message message) throws Exception {
        Account account = accountService.findById(message.getIdUser());
        RoomChat roomChat = roomChatService.getRoomChatById(1);
        Chat chat = new Chat();
        chat.setAccount(account);
        chat.setRoomChat(roomChat);
        chat.setContent(message.getMessage());
        chat.setDateTime(LocalDateTime.now());
        chatService.save(chat);
        List<Account> accountsOnline = accountService.getAccountByStatus("online", message.getIdUser());
        ResultSocket resultSocket = new ResultSocket(message.getIdFriend(), message.getIdUser(), chat, "chat");
        for (Account a : accountsOnline) {
            simpMessagingTemplate.convertAndSend("/topic/" + a.getId(), resultSocket);
//            Notification notification = new Notification(0, "Bạn có tin nhắn mới", false, new Account(message.getIdUser()), message.getIdFriend());
//            notificationService.save(notification);
        }
        simpMessagingTemplate.convertAndSend("/topic/" + message.getIdUser(), resultSocket);
    }
}