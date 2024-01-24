package com.User.dto;

import com.User.domain.Client;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public class ManagerCreateDto extends AdminCreateDto{
    private Date datumZaposljavanja;
    private String nazivFisSale;
    private Boolean isBanovan;

    private List<Client> clients;

    public Boolean getBanovan() {
        return isBanovan;
    }

    public void setBanovan(Boolean banovan) {
        isBanovan = banovan;
    }
    public Date getDatumZaposljavanja() {
        return datumZaposljavanja;
    }

    public void setDatumZaposljavanja(Date datumZaposljavanja) {
        this.datumZaposljavanja = datumZaposljavanja;
    }

    public String getNazivFisSale() {
        return nazivFisSale;
    }

    public void setNazivFisSale(String nazivFisSale) {
        this.nazivFisSale = nazivFisSale;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
