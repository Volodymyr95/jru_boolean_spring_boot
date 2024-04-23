package com.codegym.app.web.mvc;

import com.codegym.app.model.dto.UserDto;
import com.codegym.app.model.entity.User;
import com.codegym.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MvcUserController {

    private final UserService userService;

    @GetMapping("/mvc/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping("/mvc/users")
    public String createUser(@Valid UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        userService.createUser(user);
        return "redirect:/mvc/users";
    }

    @GetMapping("/mvc/delete/users/user/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        userService.delete(userId);
        return "redirect:/mvc/users";
    }


}
