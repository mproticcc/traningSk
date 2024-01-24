package raf.userservice;

import com.User.domain.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ManagerDto {

    private Long id;

    private Date datumZaposljavanja;

    private String nazivFisSale;

    private Boolean isBanovan;

    private List<Client> clients;

}
