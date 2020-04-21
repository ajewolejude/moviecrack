package com.greychain.wootlabproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {



    @GetMapping("/login")
    public String viewLoginPage(){

        return "login";
    }

    @GetMapping("/register")
    public String viewRegPage(){

        return "registration";
    }

}
