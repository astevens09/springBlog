package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

    @GetMapping("/hello/{name}")
    private String test(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
