package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/{num2}")
    @ResponseBody
    protected int addFunction(@PathVariable int num1, @PathVariable int num2){
        return num1+num2;
    }

    @GetMapping("/subtract/{num1}/{num2}")
    @ResponseBody
    protected double subtractFunction(@PathVariable double num1, @PathVariable double num2){
        return num2-num1;
    }

    @RequestMapping(path = "/multiply/{num1}/{num2}", method = RequestMethod.GET)
    @ResponseBody
    protected double productFunction(@PathVariable double num1, @PathVariable double num2){
        return num1*num2;
    }

    @RequestMapping(path = "/divide/{num1}/{num2}", method = RequestMethod.GET)
    @ResponseBody
    protected double divideFunction(@PathVariable double num1, @PathVariable double num2){
        return num1/num2;
    }

}
