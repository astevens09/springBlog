package com.example.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    private String rollDiceGetController(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    private String rollDiceVarGetController(@PathVariable int num, Model model){

        Random rand = new Random();
        int upperBound = 6;
        int randNum = 1+rand.nextInt(upperBound);

        model.addAttribute("userNum", num);
        model.addAttribute("compNum", randNum);

        return "roll-dice";
    }
}
