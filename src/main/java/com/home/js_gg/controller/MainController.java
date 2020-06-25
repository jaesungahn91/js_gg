package com.home.js_gg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView showMainPage(WebRequest request, Model model){
        return new ModelAndView("index");
    }
}
