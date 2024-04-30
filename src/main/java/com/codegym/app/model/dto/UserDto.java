package com.codegym.app.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Size(min = 3, message = "Cannot be less that 3")
    private String firstName;
    @Size(min = 3, message = "Cannot be less that 3")
    private String lastName;
    @Email(message = "Invalid email")
    private String email;
    @Min(value = 0, message = "Cannot be less that 0")
    private int age;
}
