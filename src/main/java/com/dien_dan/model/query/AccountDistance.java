package com.dien_dan.model.query;

import com.dien_dan.model.Area;
import com.dien_dan.model.Role;

import java.sql.Date;

public interface AccountDistance {
    public long getId();

    public double getDistance();

    public String getUsername();

    public String getPassword();

    public String getAvatar();

    public String getStatus();

    public String getGender();

    public Date getBirthday();

    public String getBio();

    public Area getArea();

    public Role getRole();
}
