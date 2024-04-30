package com.codegym.app.web.rest;

import com.codegym.app.model.dto.SignInDto;
import com.codegym.app.model.dto.SignInResponseDto;
import com.codegym.app.model.dto.SignUpDto;
import com.codegym.app.model.dto.UserDto;
import com.codegym.app.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public UserDto signUp(@RequestBody @Validated SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody @Validated SignInDto signUpDto) {
        return authService.signIn(signUpDto);
    }


}
