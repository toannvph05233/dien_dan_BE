package com.dien_dan.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
