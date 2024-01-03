package com.dien_dan.repository;

import com.dien_dan.model.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface INotificationRepo extends CrudRepository<Notification, Long> {
    List<Notification> findAllByAccount_IdAndStatusAndSendToAccount(long id, boolean status, long sendToAccount);
}
