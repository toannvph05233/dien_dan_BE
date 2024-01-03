package com.dien_dan.service;

import com.dien_dan.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getByIdAccount(long idF, long idM);
    Notification save(Notification notification);

}
