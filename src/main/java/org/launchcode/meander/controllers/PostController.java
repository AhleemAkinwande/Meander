package org.launchcode.meander.controllers;

import org.launchcode.meander.models.User;
import org.launchcode.meander.models.data.PostRepository;
import org.launchcode.meander.models.Post;
import org.launchcode.meander.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("post_list")

    public String displayPosts(Model model, @PathVariable(required = false) Integer userId) {
        if (userId != null) {
            Optional<User> result = userRepository.findById(userId);
            User optUser = result.get();
            model.addAttribute("title", "Posts by: " + optUser.getFirstName() + " " + optUser.getLastName());
            model.addAttribute("posts", optUser.getPosts());
        } else {
            model.addAttribute("title", "All posts");
            List<Post> posts = (List<Post>) postRepository.findAll();
            model.addAttribute("posts", posts);
        }
        return "post_list";
    }

    @GetMapping("post_single/{postId}")
    public String displayASinglePost(Model model, @PathVariable Integer postId) {

        Optional optPost = postRepository.findById(postId);
        if (!optPost.isEmpty()) {
            Post post = (Post) optPost.get();
            model.addAttribute("post", post);
            model.addAttribute("title", "Single post");
            return "post_single";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("delete")
    public String deletePost(Model model, @RequestParam(required = true) Integer postId) {
        postRepository.deleteById(postId);
        return "redirect:post_list";
    }


    @GetMapping("create")
    public String displayPostForm(Model model) {
        model.addAttribute("title", "Create Post");


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails) principal).getUsername());

        Post post = new Post();
        post.setUser(currentUser);

        model.addAttribute(post);

        return "post_form";
    }

    @PostMapping("create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Errors Found");
            return "post_form";
        }

        postRepository.save(post);
        return "redirect:post_list";
    }
}