package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloSpringController {
    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String hello(@RequestParam String coder) {
        return String.format("Hello, %s!", coder.trim());
    }

    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloAgain(@PathVariable String name) {
        return String.format("Hello $s!", name);
    }


    @GetMapping ("form")
    public String helloForm(){
        return "form";
    }
}
