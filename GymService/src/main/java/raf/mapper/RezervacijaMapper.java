package raf.mapper;

import org.springframework.stereotype.Component;
import raf.domain.Rezervacija;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;

@Component
public class RezervacijaMapper {
    public RezervacijaDto DomainObjectToDto(Rezervacija rezervacija){
       RezervacijaDto rezervacijaDto = new RezervacijaDto();
       rezervacijaDto.setRezervacija_id(rezervacija.getRezervacija_id());
       rezervacijaDto.setCenaTreninga(rezervacija.getCenaTreninga());
       rezervacijaDto.setRezervisaniTrening(rezervacija.getRezervisaniTrening());
       rezervacijaDto.setClientID(rezervacija.getClientID());
       return rezervacijaDto;
    }

    public Rezervacija DtoToDomainObject(RezervacijaCreateDto rezervacijaCreateDto){
       Rezervacija rezervacija = new Rezervacija();
       rezervacija.setClientID(rezervacijaCreateDto.getClientID());
       rezervacija.setRezervisaniTrening(rezervacijaCreateDto.getRezervisaniTrening());
       rezervacija.setCenaTreninga(rezervacijaCreateDto.getCenaTreninga());
       //rezervacija.setRezervacija_id(rezervacijaCreateDto.getRezervacija_id());
       return rezervacija;
    }
}
