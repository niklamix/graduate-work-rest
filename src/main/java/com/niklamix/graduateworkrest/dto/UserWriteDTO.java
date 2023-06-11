package com.niklamix.graduateworkrest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserWriteDTO {
    @Size(min=3, max=255, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name can't be empty")
    private String name;
    @Size(min=3, max=255, message = "Surname must be between 3 and 50 characters")
    @NotBlank(message = "Surname can't be empty")
    private String surname;
    @Email(message = "Email is not valid")
    @NotBlank
    private String email;
    @Size(min=3, max=255, message = "Country must be between 3 and 50 characters")
    @NotBlank(message = "Country can't be empty")
    private String country;
    @Size(min=3, max=255, message = "City must be between 3 and 50 characters")
    @NotBlank(message = "City can't be empty")
    private String city;
    @Size(min=3, max=255, message = "Phone must be between 3 and 50 characters")
    @NotBlank(message = "Phone can't be empty")
    private String phone;
    private String photo;
    @Size(min=3, max=255, message = "Login must be between 3 and 50 characters")
    @NotBlank(message = "Login can't be empty")
    private String login;
    @Size(min=5, max=255, message = "Password must be between 5 and 50 characters")
    @NotBlank(message = "Password can't be empty")
    private String password;
}
