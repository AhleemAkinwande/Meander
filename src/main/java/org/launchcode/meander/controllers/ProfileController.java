package org.launchcode.meander.controllers;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
