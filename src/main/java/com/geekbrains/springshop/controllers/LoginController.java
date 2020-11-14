package com.geekbrains.springshop.controllers;

import com.geekbrains.springshop.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "login/access-denied";
    }
}
