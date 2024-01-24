package com.User.domain;

//import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client extends User{
    private int brojZakazanihTreninga=0;
    private String brojClanskeKartice;

    private boolean IsBanovan;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties("clients")
    private Manager manager;



}
