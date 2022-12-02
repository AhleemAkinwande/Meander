package org.launchcode.meander.controllers;

import org.launchcode.meander.models.User;
import org.launchcode.meander.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/user-profile")
    public String showUserProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("title", "View my profile");
        User currentUser = userRepo.findByEmail(user.getEmail());
        model.addAttribute("users", currentUser);

        return "user-profile";
    }
}
