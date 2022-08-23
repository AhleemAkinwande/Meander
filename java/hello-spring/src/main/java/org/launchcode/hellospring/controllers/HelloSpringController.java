package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class HelloSpringController {
    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(@RequestParam String coder) {
        return String.format("Hello, %s!", coder.trim());
    }

    @GetMapping
    public String helloForm(){
        String html =
                "<html>" +
                        "<body>" +
                            "<form method = 'get' action = '/hello'>" +
                                "<input type = 'text' name = 'coder' />" +
                                "<input type = 'submit' value = 'Greet Me!' />" +
                            "</form>" +
                        "</body>" +
                "</html>";
        return html;
    }
}
