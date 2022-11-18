package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    private String landingPageController(){
        return "This is the landing page!";
    }

    @GetMapping("/home")
    private String homeController(){
        return "home";
    }
}
