package com.example.springblog.controllers;

import com.example.springblog.models.Post;
//import com.example.springblog.respositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
//    private final PostRepository postDao;
//
//    public PostController(PostRepository postDao) {
//        this.postDao = postDao;
//    }

    @GetMapping("/posts")
    private String postsIndex(Model model){
        List<Post> posts = List.of(
                new Post(1,"1 post","1 body"),
                new Post(2,"2 post","2 body"),
                new Post(3,"3 post","3 body"),
                new Post(4,"4 post","4 body")
        );

        model.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    private String postsId(@PathVariable int id,Model model){
        Post post = new Post(id,"first","My first post");
        model.addAttribute("post",post);
        return "posts/show";
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
