package com.User.dto;

import com.User.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AdminCreateDto {
    private String email;
    private String firstName;
    private String lastName;
    @NotBlank
    private String username;
    private String password;
    private Date datumRodjenja;
    private Role role;

}
