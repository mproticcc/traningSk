package com.User.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email", unique = true)})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date datumRodjenja;
    private String firstName;
    private String lastName;

    @ManyToOne(optional = false)
    private Role role;
}
