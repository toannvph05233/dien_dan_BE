package com.dien_dan.model.dto;

import com.dien_dan.model.Account;
import com.dien_dan.model.Area;
import com.dien_dan.model.Notification;
import com.dien_dan.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountNotification {
    private long id;
    private String username;
    private String avatar;
    private String status;
    private String gender;
    private Date birthday;
    private String bio;
    private Area area;
    private Role role;
    private List<Notification> notifications;


    public void renderByAccount(Account account){
        this.id = account.getId();
        this.username = account.getUsername();
        this.avatar = account.getAvatar();
        this.status = account.getStatus();
        this.gender = account.getGender();
        this.birthday = account.getBirthday();
        this.bio = account.getBio();;
        this.area = account.getArea();
        this.role = account.getRole();
    }

}
