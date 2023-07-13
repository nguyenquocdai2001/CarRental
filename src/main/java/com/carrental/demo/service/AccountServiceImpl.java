package com.carrental.demo.service;

import com.carrental.demo.model.Account;
import com.carrental.demo.repository.AccountRepository;
import com.carrental.demo.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private  AccountRepository accountRepository;

    @Override
    public void addAccount(Account account) {
        accountRepository.insert(account);
    }

    @Override
    public boolean checkLogin(String email, String password, Model model) {
        String encryptedpassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        Optional<Account> optionalUser = accountRepository.getAccountByEmail(email);
        if(email.length() < 1) {
            model.addAttribute("message", new Message("warning", "Please enter email"));
        }
        else if(password.length() < 1) {
            model.addAttribute("message", new Message("warning", "Please enter password"));
        }
        else if(optionalUser.isPresent() && !(optionalUser.get().getPassword().equals(encryptedpassword))) {
            model.addAttribute("message", new Message("warning", "Email or Password is not correct"));
        }
        else if(!(optionalUser.isPresent())) {
            model.addAttribute("message", new Message("warning", "Email is not exist"));
        }
        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(encryptedpassword)) {
            return true;
        }
        return false;
    }
}
