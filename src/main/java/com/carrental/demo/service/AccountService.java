package com.carrental.demo.service;

import com.carrental.demo.model.Account;
import org.springframework.ui.Model;

public interface AccountService {
    void addAccount(Account account);
    boolean checkLogin(String email, String password, Model model);
}
