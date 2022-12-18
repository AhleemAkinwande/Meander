package org.launchcode.meander.controllers;

import org.launchcode.meander.models.data.LocationRepository;
import org.launchcode.meander.models.data.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("location")
    public String displaySearchForm(Model model) {
        model.addAttribute("locations", locationRepository.findAllLocationsStateAsc());

        return "search_form";
    }

    @GetMapping("location/{locationId}")
    public String searchByLocation(Model model, @PathVariable Integer locationId) {

        Optional optLocation = locationRepository.findById(locationId);
        if(optLocation.get() == null) {
            return "redirect:";
        } else {
            model.addAttribute("location", optLocation.get());
        }

        model.addAttribute("posts", postRepository.findByLocationId(locationId));
        return "location";
    }

    //TODO: Add search by state
}
