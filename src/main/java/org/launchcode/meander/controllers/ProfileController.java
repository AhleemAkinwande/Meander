package org.launchcode.meander.controllers;

import java.security.Principal;

import org.launchcode.meander.models.Post;
import org.launchcode.meander.models.User;
import org.launchcode.meander.models.data.UserRepository;
import org.launchcode.meander.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/user-profile")
    public String showUserProfile(Model model) {
        model.addAttribute("title", "View my profile");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.findByEmail(((UserDetails)principal).getUsername());
        model.addAttribute("user", currentUser);

        return "user-profile";
    }

    @GetMapping("/user-edit")
    public String showUserProfileForm(Model model) {
        model.addAttribute("title", "Edit my profile");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.findByEmail(((UserDetails)principal).getUsername());
        model.addAttribute("user", currentUser);

        return "user-edit";
    }

    @PostMapping("/user-edit")
    public String processPost(@RequestParam String phone, @RequestParam String location,
                              @RequestParam String facebook, @RequestParam String instagram,
                              Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.findByEmail(((UserDetails)principal).getUsername());

        if (phone.length() > 0) {
            currentUser.setPhone(phone);
        }
        if (location.length() > 0) {
            currentUser.setLocation(location);
        }
        if (facebook.length() > 0) {
            currentUser.setFacebook(facebook);
        }
        if (instagram.length() > 0) {
            currentUser.setInstagram(instagram);
        }

        userRepo.save(currentUser);

        return "redirect:user-profile";
    }
}
