package com.carrental.demo.controller;

import com.carrental.demo.model.Account;
import com.carrental.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("homepage")
    public String homepage(){
        return "./home/homepage";
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("checkLogin")
    public String checkLogin(Model model, @RequestParam("email") String email,
                             @RequestParam("password") String password, @ModelAttribute("account") Account account){
        if(accountService.checkLogin(email, password, model)){
            System.out.println("login thanh cong");
            return "redirect:/homepage";
        }
        System.out.println("login that bai");
        return "redirect:/login";
    }
}
