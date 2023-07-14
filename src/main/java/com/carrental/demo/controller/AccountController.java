package com.carrental.demo.controller;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Client;
import com.carrental.demo.repository.AccountRepository;
import com.carrental.demo.repository.ClientRepository;
import com.carrental.demo.service.AccountService;

import jakarta.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AccountController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("homepage")
    public String homepage(){
        return "./home/homepage";
    }
    
    @GetMapping("clientpage")
    public String clientpage(){
        return "./home/clientpage";
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(Model model, @RequestParam("email") String email,
                             @RequestParam("password") String password, @ModelAttribute("account") Account account, HttpSession session){
        if(accountService.checkLogin(email, password, model)){
        	Optional<Account> acc = accountRepository.getAccountByEmail(email);
        	Optional<Client> client = clientRepository.getClientByEmail(email);
        	

			System.out.println("login thanh cong");
			// set session
			session.setAttribute("userSession", acc.get());
			session.setAttribute("idUser", acc.get().getId());
			session.setAttribute("USERNAME", email);
			
			// session này dùng cho hiển thị fullname của người dùng ở header
			session.setAttribute("helloUser", client.get().getName());
			
			// session này dùng cho hiển thị các chức năng của người dùng admin hoặc client ở sidebars
			session.setAttribute("userRole", acc.get().getRole());
        	if(acc.get().getRole().equals("client")) {
        		return "redirect:/clientpage";
        	}
            System.out.println("login thanh cong");
            return "redirect:/homepage";
        }else {
        	System.out.println("login that bai");
        }
        return "login";
    }
    
    @GetMapping("/register")
	public String showAddUserForm(Model model) {
		return "register";
	}

	@PostMapping("/checkRegister")
	public String addUser(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "password") String password,
			@RequestParam(required = false, name = "confirmPassword") String confirmPassword,
			@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "phone") String phone,
			@RequestParam(required = false, name = "address") String address,
			@RequestParam(required = false, name = "tax_code") String tax_code, Model model) {

		String encryptedpassword = null;
		try {
			/* MessageDigest instance for MD5. */
			MessageDigest m = MessageDigest.getInstance("MD5");

			/* Add plain-text password bytes to digest using MD5 update() method. */
			m.update(password.getBytes());

			/* Convert the hash value into bytes */
			byte[] bytes = m.digest();

			/*
			 * The bytes array has bytes in decimal form. Converting it into hexadecimal
			 * format.
			 */
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			/* Complete hashed password in hexadecimal format */
			encryptedpassword = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (accountService.checkRegister(name, email, password, confirmPassword, phone, address, tax_code, model)) {
			System.out.println("Register thanh cong");
			Account acc = new Account(email, encryptedpassword, "activated", "client");
			Client client = new Client(name, phone, email, address, tax_code);
			accountRepository.insert(acc);
			clientRepository.insert(client);
			model.addAttribute("ACC", acc);
			model.addAttribute("CLIENT", client);
			return "register";
		} else {
			model.addAttribute("valid", "is-invalid");
			model.addAttribute("check", new Account(email, password,"activated", "client"));
			model.addAttribute("check2", new Client(name, phone, email, address, tax_code));
			System.out.println("Register that bai");
		}
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("Logout thanh cong");
		return "redirect:/login";
	}
}
