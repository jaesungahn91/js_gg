package com.home.js_gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/logout")
    public void logout(){

    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "login/denied";
    }
}
