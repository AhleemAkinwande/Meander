package org.launchcode.meander.controllers;

import org.launchcode.meander.models.ActivityType;
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

        return "location_search";
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

    @GetMapping("activity/{activityName}")
    public String searchByActivity(Model model, @PathVariable String activityName) {

        try {
            String description = ActivityType.valueOf(activityName.toUpperCase()).getDescription();
            model.addAttribute("title", activityName);
            model.addAttribute("posts", postRepository.findPostsByActivity(activityName.toUpperCase()));
            model.addAttribute("description", description);

        } catch(Exception e) {
            model.addAttribute("message", "Either the tag #" + activityName + " doesn't exist yet, or there are no matching posts \uD83D\uDE12");
        }

        return "activity";
    }

    @GetMapping("activity")
    public String displayActivitySearchForm(Model model) {
        model.addAttribute("activities", ActivityType.showAllActivities());
        return "activity_search";
    }

    //TODO: Add search by state
}
