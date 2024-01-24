package com.User.dto;

import com.User.domain.Manager;
import com.sun.jna.IntegerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends AdminDto{
    private Integer brojZakazanihTreninga;

    private String brojClanskeKartice;

    private Boolean isBanovan;

    private Manager manager;

    private String activationMail;


}
