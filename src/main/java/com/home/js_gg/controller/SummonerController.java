package com.home.js_gg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SummonerController {

    @GetMapping("test")
    public String test(){
        return "main/main";
    }
}
