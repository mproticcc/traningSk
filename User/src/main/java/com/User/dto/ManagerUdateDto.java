package com.User.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ManagerUdateDto {
    private String firstName;
    private String lastName;
    private Date datumRodjenja;
    private String username;
    private String password;
    private String email;
}
