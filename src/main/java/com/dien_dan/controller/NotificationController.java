package com.dien_dan.controller;

import com.dien_dan.model.Notification;
import com.dien_dan.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("notifications")
public class NotificationController {
    @Autowired
    INotificationService notificationService;

    @GetMapping("{idM}/{idF}")
    public ResponseEntity<?> setNotification(@PathVariable long idM, @PathVariable long idF){
        List<Notification> notifications = notificationService.getByIdAccount(idF,idM);
        for (Notification o:notifications) {
            o.setStatus(true);
            notificationService.save(o);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
