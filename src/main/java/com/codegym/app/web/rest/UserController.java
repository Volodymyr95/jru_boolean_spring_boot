package com.codegym.app.web.rest;

import com.codegym.app.model.dto.UpdateUserDto;
import com.codegym.app.model.dto.UserDto;
import com.codegym.app.model.entity.User;
import com.codegym.app.service.UserService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Validated UserDto user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    public List<User> getAllByFirstName(@RequestParam(required = false) String firstName,
                                        @RequestParam(required = false) String lastName) {
        return userService.getAllByFirstOrLastName(firstName, lastName);
    }

    @GetMapping(value = "/user/search")
    public List<User> searchUser(@RequestParam String firstNamePrefix) {
        return userService.searchAllByPrefixFirstName(firstNamePrefix);
    }

    @GetMapping(value = "/adults")
    public List<User> getAllAdultUsers() {
        return userService.getAllAdultUsers();
    }

    @PutMapping
    public Long update(@RequestBody User user) {
        return userService.update(user);
    }

    @PatchMapping("/user/{userId}")
    public void partialUpdate(@PathVariable Long userId, @RequestBody @Validated UpdateUserDto userDto) {
        userService.partialUpdate(userId, userDto);
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PatchMapping(value = "/user/{id}/email/{email}")
    public void changeUserEmail(@PathVariable Long id, @PathVariable @Email(message = "Invalid email format") String email) {
        userService.changeUserEmail(id, email);
    }


}
