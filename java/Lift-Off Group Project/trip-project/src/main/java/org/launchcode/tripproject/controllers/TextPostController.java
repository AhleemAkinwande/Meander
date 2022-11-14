package org.launchcode.tripproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TextPostController {
    // localhost:8080

    @GetMapping("textpost")
    public String textPost(@RequestParam String newpost) {
        return newpost;
    }


    @GetMapping("postform")
    public String postFrom() {
        return "<html>" +
                "<body>" +
                "<form method = 'get' action = '/textpost'>" +
                "<input type =  'text' name = 'newpost' />" +
                "<input type = 'submit' value = 'Post'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
