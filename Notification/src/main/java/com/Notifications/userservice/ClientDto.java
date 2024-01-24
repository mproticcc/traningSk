package com.Notifications.userservice;

import com.User.domain.Client;
import com.User.domain.Manager;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ClientDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Date datumRodjenja;
    private Integer brojZakazanihTreninga;
    private Integer brojClanskeKartice;
    private Boolean isBanovan;
    private Manager manager;

}
