package com.greychain.wootlabproject.exception.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {


    @GetMapping("/")
    public String viewHomePage(){

        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(){

        return "login";
    }

    @GetMapping("/register")
    public String viewRegPage(){

        return "registration";
    }

}
