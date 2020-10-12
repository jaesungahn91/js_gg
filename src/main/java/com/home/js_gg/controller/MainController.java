package com.home.js_gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {

    @GetMapping("/mainView")
    public String getMainView(){
        return "main/main";
    }
}
