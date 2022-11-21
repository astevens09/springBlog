package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.respositories.PostRepository;
import com.example.springblog.respositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    private String postsIndex(Model model){

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("users",userDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    private String postsId(@PathVariable long id,Model model){
        Optional<Post> post = postDao.findById(id);
        Optional<User> user = userDao.findById(post.get().getUser().getId());
        model.addAttribute("post",post.get());
        model.addAttribute("user", user.get());
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

        Optional<User> newUser = userDao.findById(1L);
        Post post = new Post(title,body, newUser.get());

        postDao.save(post);

        return "redirect:/posts";
    }

}
