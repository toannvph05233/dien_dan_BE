package com.dien_dan.controller;


import com.dien_dan.model.Account;
import com.dien_dan.model.Area;
import com.dien_dan.model.Role;
import com.dien_dan.model.RoomChat;
import com.dien_dan.model.dto.AccountToken;
import com.dien_dan.repository.IRoleRepo;
import com.dien_dan.service.IAccountService;
import com.dien_dan.service.IAreaService;
import com.dien_dan.service.IRoomChatService;
import com.dien_dan.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@CrossOrigin("*")
public class LoginApi {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleRepo iRoleRepo;

    @Autowired
    IAreaService areaService;

    @Autowired
    IRoomChatService roomChatService;


    @PostMapping("/login")
    public AccountToken getLogin(@RequestBody Account account){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        account = accountService.getAccountLogin(account.getUsername(), account.getPassword());
        String token = jwtService.createToken(authentication);

        return new AccountToken(account.getId(), account.getUsername(), token, account.getAvatar(), account.getRole());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account){
        Account account1 = accountService.getAccountByUsername(account.getUsername());
        if (account1 == null) {
            Role role = new Role();
            role.setId(1);
            Area area = new Area();
            area.setId(1);
            account.setArea(area);
            account.setRole(role);
            account.setStatus("online");
            account.setGender("nam");
            account.setBirthday(new Date(System.currentTimeMillis()));
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("init")
    public ResponseEntity<?> init(){
        Role role1 = new Role(1, "ROLE_USER");
        Role role2 = new Role(2, "ROLE_ADMIN");
        iRoleRepo.save(role1);
        iRoleRepo.save(role2);
        Area area = new Area(1,1,1);
        areaService.save(area);

        RoomChat roomChat = new RoomChat(1,"all", 2);
        roomChatService.save(roomChat);

        Account account = new Account(1, "https://bigtop.vn/blog/wp-content/uploads/2022/02/imessege.jpg", "Giao Lưu", "123", "Chat ALL", role2, "all", "nam", area);
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
