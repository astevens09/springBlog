package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.respositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    private String postsIndex(Model model){

        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    private String postsId(@PathVariable long id,Model model){
        Optional<Post> post = postDao.findById(id);
        model.addAttribute("post",post.get());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    private String postsCreateGet(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    private String postsCreatePost
            (@RequestParam(name = "title")String title,
             @RequestParam(name = "body")String body){
        Post post = new Post(title,body);

        postDao.save(post);

        return "redirect:/posts";
    }

}
