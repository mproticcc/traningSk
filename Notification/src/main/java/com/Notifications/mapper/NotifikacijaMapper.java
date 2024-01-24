package com.Notifications.mapper;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.NotifikacijaDto;
import com.Notifications.dto.NotifikacijeCreateDto;
import org.springframework.stereotype.Component;

@Component
public class NotifikacijaMapper {

    public NotifikacijaDto notifikacijaToDto(Notifikacija notifikacija){
        NotifikacijaDto notifikacijaDto = new NotifikacijaDto();
        notifikacijaDto.setId(notifikacija.getId());
        notifikacijaDto.setTekst(notifikacija.getText());
        notifikacijaDto.setLink(notifikacija.getLink());
        notifikacijaDto.setTipNotifikacije(notifikacija.getTipNotifikacije());
        notifikacijaDto.setVremeSlanja(notifikacija.getDatumSlanja());
        notifikacijaDto.setClinetID(notifikacija.getClientID());
        return notifikacijaDto;
    }

    public Notifikacija notifikacijaCreateDto(NotifikacijeCreateDto nCDto){
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setLink(nCDto.getLink());
        notifikacija.setText(nCDto.getTekst());
        notifikacija.setDatumSlanja(nCDto.getVremeSlanja());
        notifikacija.setTipNotifikacije(nCDto.getTipNotifikacije());
        notifikacija.setClientID(nCDto.getClientId());
        return notifikacija;
    }
}
