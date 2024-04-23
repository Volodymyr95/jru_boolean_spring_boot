package com.codegym.app.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcIndexController {

    @GetMapping("/mvc")
    public String index() {
        return "redirect:/mvc/users";
    }

}
