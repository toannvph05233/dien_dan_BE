package com.dien_dan.service.impl;


import com.dien_dan.model.Account;
import com.dien_dan.model.query.AccountDistance;
import com.dien_dan.repository.IAccountRepo;
import com.dien_dan.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public Account getAccountLogin(String username, String password) {
        return iAccountRepo.getAccountLogin(username,password);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return iAccountRepo.getAccountByUsername(username);
    }

    @Override
    public Account findById(long id) {
        return iAccountRepo.findById(id).get();
    }

    @Override
    public Account save(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) iAccountRepo.findAll();
    }

    @Override
    public List<Account> getAccountByStatus(String status, int id) {
        return iAccountRepo.getAccountByStatus(status, id);
    }

    @Override
    public List<Account> getAllByAreaIdAndStatus(long id, String status) {
        return iAccountRepo.getAllByAreaIdAndStatus(id,status);
    }

    @Override
    public List<AccountDistance> getAllByAreaIdAndStatusAndKm(long idUser) {
        return iAccountRepo.getAllByAreaIdAndStatusAndKm(idUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getUsername(),account.getPassword(),roles);
    }
}
