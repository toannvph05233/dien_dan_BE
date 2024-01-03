package com.dien_dan.model.dto;

import com.dien_dan.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String username;
    private String token;
    private String avatar;
    private Role role;
}
