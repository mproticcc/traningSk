package raf.userservice;

import com.User.domain.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private Long id;

    private Integer brojZakazanihTreninga;

    private Integer brojClanskeKartice;

    private Boolean isBanovan;

    private Manager manager;

    private String email;

}
