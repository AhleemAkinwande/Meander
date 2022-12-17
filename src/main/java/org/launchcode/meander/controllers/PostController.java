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

    public String displayPosts(@RequestParam(required=false) Integer userId, Model model){

        if(userId == null) {
            model.addAttribute("title", "All posts");
            List<Post> posts = (List<Post>) postRepository.findAll();
            model.addAttribute("posts", posts);
        } else {
          Optional<User> result = userRepository.findById(userId);
          if(result.isEmpty()) {
              model.addAttribute("title", "Invalid user ID.");
          } else {
             User user = result.get();
              model.addAttribute("title", "Posts by: " + user.getFirstName() + " " + user.getLastName());
              model.addAttribute("posts", user.getPosts());
          }
        }
        return "post_list";
    }

    @GetMapping("post_single/{postId}")
    public String displayASinglePost(Model model, @PathVariable Integer postId){

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

    @GetMapping("create")
    public String displayPostForm(Model model){
        model.addAttribute("title", "Create Post");


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails)principal).getUsername());

        Post post = new Post();
        post.setUser(currentUser);

        model.addAttribute(post);

        return "post_form";
    }

    @PostMapping("create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, Model model){

        if(errors.hasErrors()) {
            model.addAttribute("title", "Errors Found");
            return "post_form";
        }

        postRepository.save(post);
        return "redirect:post_list";
    }

    @PostMapping("delete")
    public String renderDeleteEventForm(@RequestParam(required = false) int[] postId){

        if(postId != null){
            for (int id: postId){
                postRepository.deleteById(id);
                //EventData.remove(id);
            }
        }

        return "redirect:";
    }
}
