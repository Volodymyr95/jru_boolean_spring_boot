package com.codegym.app.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @Size(min = 3, message = "Fist name cannot be less that 3")
    private String firstName;
    @Size(min = 3, message = "Last name cannot be less that 3")
    private String lastName;
    @Email(message = "Invalid email")
    private String email;
    @Size(max = 255, message = "Password cannot be grater that 255 symbols")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Invalid password")
    private String password;
    @Min(value = 0, message = "Age cannot be less that 0")
    private int age;

}
