package com.User.dto;

import com.User.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AdminDto {
    private Long id;

    private Role role;
    private String email;
    private String firstName;
    private String lastName;

    private String password;
    private String username;
    private Date datumRodjenja;


}
