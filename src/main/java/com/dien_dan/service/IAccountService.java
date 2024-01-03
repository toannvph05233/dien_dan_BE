package com.dien_dan.service;


import com.dien_dan.model.Account;
import com.dien_dan.model.query.AccountDistance;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Account getAccountLogin(String username, String password);
    Account getAccountByUsername(String username);
    Account findById(long id);
    Account save(Account account);
    List<Account> getAll();
    List<Account> getAccountByStatus(String status, int id);

    List<Account> getAllByAreaIdAndStatus(long id, String status);


    List<AccountDistance> getAllByAreaIdAndStatusAndKm(long idUser);



}
