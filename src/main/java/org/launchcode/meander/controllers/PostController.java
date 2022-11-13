package org.launchcode.meander.controllers;

import org.launchcode.meander.data.PostRepository;
import org.launchcode.meander.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String displayPosts(Model model){
        model.addAttribute("title", "All posts");
        model.addAttribute("posts", postRepository.findAll());
        return "post_list";
    }

    @GetMapping("singlePost")
    public String displayASinglePost(@RequestParam Integer postId,Model model){
        model.addAttribute("title", "Single post");
        model.addAttribute("post", postRepository.findById(postId));
        return "single_post";
    }

    @GetMapping("create")
    public String displayPostForm(Model model){
        model.addAttribute("title", "Create Post");
        model.addAttribute(new Post());
        return "post_form";
    }

    @PostMapping("create")
    public String processPost(@ModelAttribute @Valid Post post, Errors erros, Model model){

        if(erros.hasErrors()){
            model.addAttribute("title", "Create Post");
            model.addAttribute(post);
            return "post_form";
        }
        postRepository.save(post);
        return "redirect:";
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
