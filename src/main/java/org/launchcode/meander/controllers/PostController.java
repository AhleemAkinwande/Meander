package org.launchcode.meander.controllers;

import org.launchcode.meander.models.ImageUploadModel;
import org.launchcode.meander.models.data.PostRepository;
import org.launchcode.meander.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("post_list")
    public String displayPosts(Model model){
        model.addAttribute("title", "All posts");
        List<Post> posts = (List<Post>) postRepository.findAll();
        model.addAttribute("posts", posts);
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
        model.addAttribute(new Post());
        return "post_form";
    }

    @PostMapping("create")
    public String processPost(@RequestParam String title, @RequestParam String text,
                              @ModelAttribute @Valid Post post, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Post");
            model.addAttribute(post);
            return "post_form";
        }
        post = new Post(title, text);
        postRepository.save(post);
        return "redirect:post_list";
    }

    //I created a separate method for uploading the images because I was not sure how to use it with the original "create" method above.
    @PostMapping(value = {"/upload_image"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Post postImage(@RequestPart("post") Post post,
                            @RequestPart("imageFile") MultipartFile[] file) {

        try {
            Set<ImageUploadModel> images = uploadImage(file);
            post.setPostImages(images);
            return postRepository.postImage(post);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageUploadModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageUploadModel> postImages = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageUploadModel imageUploadModel = new ImageUploadModel(
            file.getOriginalFilename(),
            file.getContentType(),
            file.getBytes() // will throw an IOException, the throws IOException above mitigates that and the try catch above also handles that case.
            );
            postImages.add(imageUploadModel);
        }
        return postImages;
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
