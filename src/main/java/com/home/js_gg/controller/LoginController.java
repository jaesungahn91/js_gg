package com.home.js_gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/logout")
    public void logout(){

    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "login/denied";
    }


}
