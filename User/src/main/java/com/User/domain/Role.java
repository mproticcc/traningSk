package com.User.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Role() {
    }
    public Role(String name){
        this.name = name;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
