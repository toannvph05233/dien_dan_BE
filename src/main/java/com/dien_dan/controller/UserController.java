package com.dien_dan.controller;

import com.dien_dan.model.Account;
import com.dien_dan.model.dto.AccountNotification;
import com.dien_dan.model.query.AccountDistance;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IAccountService accountService;
    @Autowired
    INotificationService notificationService;

    @GetMapping("{id}")
    public List<AccountNotification> getAccountOnline(@PathVariable int id) {
        List<Account> accounts = accountService.getAccountByStatus("online", id);
        List<AccountNotification> accountNotifications = new ArrayList<>();
        for (Account a:accounts) {
            AccountNotification accountNotification = new AccountNotification();
            accountNotification.renderByAccount(a);
            accountNotification.setNotifications(notificationService.getByIdAccount(a.getId(), id));
            accountNotifications.add(accountNotification);
        }
        return accountNotifications;
    }

    @GetMapping("profile/{id}")
    public Account profile(@PathVariable int id) {
        return accountService.findById(id);
    }

    @GetMapping("status/off/{id}")
    public ResponseEntity<?> offline(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setStatus("offline");
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("status/on/{id}")
    public ResponseEntity<?> online(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setStatus("online");
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/areas/{idUser}")
    public List<AccountDistance> getAccountOnlineByArea(@PathVariable int idUser) {
        return accountService.getAllByAreaIdAndStatusAndKm(idUser);
    }

//    @GetMapping("/admin")
//    public String admin(){
//        return "day la admin";
//    }
}
