package com.codegym.app.web.mvc;

import com.codegym.app.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpMvcController {

    @GetMapping("/signup")
    public String redirectToSignUp(User user) {
        return "add-user";
    }
}
