package com.carrental.demo.service;

import com.carrental.demo.model.Account;
import org.springframework.ui.Model;

public interface AccountService {
    boolean checkLogin(String email, String password, Model model);
    boolean checkRegister(String name, String email, String password, String confirmPassword, String phone,
			String address, String tax_code, Model model);
}
