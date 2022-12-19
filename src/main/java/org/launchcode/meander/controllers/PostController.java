package org.launchcode.meander.controllers;

import org.launchcode.meander.models.Location;
import org.launchcode.meander.models.User;
import org.launchcode.meander.models.data.LocationRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("post_list")

    public String displayPosts(@RequestParam(required = false) Integer userId, Model model) {

        if (userId == null) {
            model.addAttribute("title", "All posts");
            List<Post> posts = (List<Post>) postRepository.findAll();
            model.addAttribute("posts", posts);
        } else {
            Optional<User> result = userRepository.findById(userId);
            if (result.isEmpty()) {
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
    public String displayASinglePost(Model model, @PathVariable Integer postId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails) principal).getUsername());

        Optional optPost = postRepository.findById(postId);

        if (!optPost.isEmpty()) {
            Post post = (Post) optPost.get();
            model.addAttribute("post", post);
            model.addAttribute("title", post.getTitle());
            return "post_single";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("delete")
    public String deletePost(Model model, @RequestParam(required = true) Integer postId, RedirectAttributes redirectAttributes) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails) principal).getUsername());

        if(postId == null) {
            return "redirect:";
        } else {
            Optional optPost = postRepository.findById(postId);
            if(!optPost.isEmpty()) {
                Post postToDelete = (Post) optPost.get();

                if(postToDelete.getUser() != currentUser) {
                    redirectAttributes.addFlashAttribute("message", "This post was created by " + postToDelete.getUser().getFirstName() + " " + postToDelete.getUser().getLastName() + ". You cannot delete it.");
                    return "redirect:/post/post_single/" + postId;
                } else {
                    postRepository.deleteById(postId);
                }
            }
        }

        return "redirect:post_list";
    }


    @GetMapping("create")
    public String displayPostForm(Model model) {
        model.addAttribute("title", "Add a Place");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails) principal).getUsername());

        Post post = new Post();
        post.setUser(currentUser);

        model.addAttribute(post);

        return "post_form";
    }

    @PostMapping("create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Failed to create post, please try again!");
            return "post_form";
        }

        // Check if location (city, state,  country) already exists to prevent duplicate entries in the location table

        Location optLocation = locationRepository.findLocationByCityStateCountry(post.getLocation().getCity(),post.getLocation().getState(),post.getLocation().getCountry());

        if(optLocation == null) {
            postRepository.save(post);
        } else {
            post.setLocation((Location) optLocation);
            postRepository.save(post);
        }

        return "redirect:post_list";
    }

    @GetMapping("edit/{postId}")
    public String displayEditPostForm(Model model, @PathVariable Integer postId, RedirectAttributes redirectAttributes) {

        model.addAttribute("title", "Edit a Post");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(((UserDetails)principal).getUsername());

        if(postId == null) {
            return "redirect:";
        } else {
            Optional optPost = postRepository.findById(postId);
            if(!optPost.isEmpty()) {

                Post postToEdit = (Post) optPost.get();

                if(postToEdit.getUser() != currentUser) {
                    redirectAttributes.addFlashAttribute("message", "This post was created by " + postToEdit.getUser().getFirstName() + " " + postToEdit.getUser().getLastName() + ". You cannot edit it.");
//
                    return "redirect:/post/post_single/" + postId;
                } else {
                    model.addAttribute("post", postToEdit);
                }
            }
        }
        return "post_edit";
    }

    @PostMapping("edit/{postId}")
    public String processEditPostForm(@ModelAttribute @Valid Post post, @PathVariable Integer postId, Model model, Errors errors) {

        if(postId != null) {
            Optional optPostToEdit = postRepository.findById(postId);
            Post postToEdit = (Post) optPostToEdit.get();

            postToEdit.setText(post.getText());
            postToEdit.setTitle(post.getTitle());

            postRepository.save(postToEdit);
        } else {
            return "redirect:/post_list";
        }


     return "redirect:/post/post_single/" + postId;
    }
}
