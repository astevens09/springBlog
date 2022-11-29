package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.respositories.PostRepository;
import com.example.springblog.respositories.UserRepository;
import com.example.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    private String postsIndex(Model model) {

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    private String postsId(@PathVariable long id, Model model) {
        Optional<Post> post = postDao.findById(id);
        Optional<User> user = userDao.findById(post.get().getUser().getId());
        model.addAttribute("post", post.get());
        model.addAttribute("user", user.get());

        return "posts/show";
    }



    @GetMapping("/posts/create")
    private String postsCreateGet(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("post",new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    protected String postsCreatePost(@ModelAttribute Post post) {

        Optional<User> newUser = userDao.findById(2L);
        Post newPost = new Post(post.getTitle(), post.getBody(), newUser.get());

        emailService.prepareAndSend(newPost,"New Post Created",newUser.get().getUserName()+" created a new post");
        postDao.save(newPost);


        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    private String postsIdEdit(@PathVariable long id, Model model) {
        Optional<Post> oldPost = postDao.findById(id);
        Optional<User> user = userDao.findById(oldPost.get().getUser().getId());

        Post newPost = new Post(id,oldPost.get().getTitle(),oldPost.get().getBody());
        newPost.setUser(user.get());
//        System.out.println(newPost.getUser().getEmail());
        model.addAttribute("newPost", newPost);
        model.addAttribute("user", user.get());


        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    protected String postEditPost(@ModelAttribute Post post){
        User user = postDao.findById(post.getId()).get().getUser();
        Post newPost = new Post(post.getId(),post.getTitle(),post.getBody(),user);
        postDao.save(newPost);
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
//        System.out.println("Edit post successful");
        return "redirect:/posts";
    }

//    @PostMapping("/posts/create")
//    protected String createPost(){
//        User user = userDao.getReferenceById(1L);
//
//        //Create form in thymeleaf for user to create post
//
//        //Get form data here
//            //create new post object and assign signed in user to the Post
//
//        //Use prepareAndSend method from EmailService class
//
//        //redirect
//
//
//    }

}
