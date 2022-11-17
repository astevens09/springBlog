package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    private String postsIndex(){
        return "Posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    private String postsId(@PathVariable int id){
        return "view an individual post "+id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    private String postsCreateGet(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    private String postsCreatePost(){
        return "create a new post";
    }

}
