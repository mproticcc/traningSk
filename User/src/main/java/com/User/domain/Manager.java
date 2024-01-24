package com.User.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Manager")
@Getter
@Setter
public class Manager extends User{
    private Date datumZaposljavanja;
    private String nazivFisSale;
    private Boolean IsBanovan;
    @Column
    @OneToMany(mappedBy = "manager")
    @JsonIgnoreProperties("manager")
    private List<Client> clients;

    private Long sala_id;
}
