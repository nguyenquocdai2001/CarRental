package com.carrental.demo.service;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.ui.Model;

public interface AccountService {
    boolean checkLogin(String email, String password, Model model) throws ExecutionException, InterruptedException;
    boolean checkRegister(String name, String email, String password, String confirmPassword, String phone,
			String address, String tax_code, Model model) throws ExecutionException, InterruptedException ;
   
}
