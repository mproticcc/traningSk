package com.Notifications.mapper;

import com.Notifications.domain.TipNotifikacije;
import com.Notifications.dto.TipNotifikacijeCreateDto;
import com.Notifications.dto.TipNotifikacijeDto;
import org.springframework.stereotype.Component;

@Component
public class TipNotifikacijeMapper {

    public TipNotifikacijeDto tipNotifikacijeDto(TipNotifikacije tipNotifikacije){
        TipNotifikacijeDto tipNotifikacijaDto = new TipNotifikacijeDto();
        tipNotifikacijaDto.setId(tipNotifikacije.getId());
        tipNotifikacijaDto.setNaziv(tipNotifikacije.getNaziv());
        return tipNotifikacijaDto;
    }

    public TipNotifikacije tipNotifikacijeCreateDto(TipNotifikacijeCreateDto tFto){
        TipNotifikacije tipNotifikacije = new TipNotifikacije();
        tipNotifikacije.setNaziv(tFto.getNaziv());
        return tipNotifikacije;
    }
}
