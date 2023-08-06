package com.carrental.demo.service;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Client;

import com.carrental.demo.repository.AccountRepositoryImpl;
import com.carrental.demo.validator.Message;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    private  AccountRepositoryImpl accountRepositoryImpl;

    @Override
    public boolean checkLogin(String email, String password, Model model) throws ExecutionException, InterruptedException {
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
        Optional<Account> optionalUser = accountRepositoryImpl.getAccountByEmail(email);
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
        	System.out.println(email);
            model.addAttribute("message", new Message("warning", "Email is not exist"));
        }
        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(encryptedpassword)) {
            return true;
        }
        return false;
    }
    
    @Override
	public boolean checkRegister(String name, String email, String password, String confirmPassword, String phone,
			String address, String tax_code, Model model) throws ExecutionException, InterruptedException {
		
		Optional<Account> optionalUser = accountRepositoryImpl.getAccountByEmail(email);
		
		
		if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(password.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter password"));
		}
		else if(password.length() < 6) {
			model.addAttribute("message", new Message("warning", "Please enter password at least 6 digits"));
		}
		else if(!(password.equals(confirmPassword))) {
			model.addAttribute("message", new Message("warning", "Password is different from Confirm Password"));
		}
		else if(confirmPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter confirm password"));
		}
		else if(name.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter fullname"));
		}
		else if(phone.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter phone number"));
		}
		else if(phone.length() != 10) {
			model.addAttribute("message", new Message("warning", "Wrong type of phone number"));
		}
		else if(address.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter address"));
		}
		else if(optionalUser.isPresent()) {
			model.addAttribute("message", new Message("warning", "Email is existed"));
		}
		else {
			model.addAttribute("message", new Message("success", "Register successfully"));
			return true;
		}
		return false;
	}

	
}
