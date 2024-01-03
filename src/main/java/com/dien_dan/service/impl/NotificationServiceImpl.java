package com.dien_dan.service.impl;

import com.dien_dan.model.Notification;
import com.dien_dan.repository.INotificationRepo;
import com.dien_dan.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    INotificationRepo iNotificationRepo;

    @Override
    public List<Notification> getByIdAccount(long idF, long idM) {
        return iNotificationRepo.findAllByAccount_IdAndStatusAndSendToAccount(idF, false, idM);
    }

    @Override
    public Notification save(Notification notification) {
        return iNotificationRepo.save(notification);
    }
}
