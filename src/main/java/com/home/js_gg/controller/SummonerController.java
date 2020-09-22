package com.home.js_gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class SummonerController {

    @GetMapping("/kk")
    public String test(){
        return "main/main";
    }
}
