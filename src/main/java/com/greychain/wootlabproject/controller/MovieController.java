package com.greychain.wootlabproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("movie")
public class MovieController {


    @RequestMapping("/{id}/view")
    public ModelAndView showDetails(@PathVariable(name = "id") int id, Model model){
        return new ModelAndView("details", "id", id);
    }


    @GetMapping("/all")
    public String viewHomePage(){

        return "index";
    }

}
