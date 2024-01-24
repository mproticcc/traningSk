package raf.mapper;

import org.springframework.stereotype.Component;
import raf.domain.Trening;
import raf.dto.TreningCreateDto;
import raf.dto.TreningDto;

@Component
public class TreningMapper {

    public TreningDto DomainObjectToDto(Trening trening){
        TreningDto tDto = new TreningDto();
        tDto.setId(trening.getTrening_id());
        tDto.setSala(trening.getSala());
        tDto.setTip(trening.getTip());
        tDto.setCenaTreninga(trening.getCenaTreninga());
        tDto.setBrRezervacija(trening.getBrRezervacija());
        tDto.setPocetakTermina(trening.getPocetakTermina());
        tDto.setKrajTermina(trening.getKrajTermina());
        tDto.setTerminTreninga(trening.getTerminTreninga());
        tDto.setGrupni(trening.isGrupni());
        return tDto;
    }

    public Trening DtoToDomainObject(TreningCreateDto trDto){
        Trening trening = new Trening();
        trening.setCenaTreninga(trDto.getCenaTreninga());
        trening.setSala(trDto.getSala());
        trening.setTip(trDto.getTip());
        trening.setPocetakTermina(trDto.getPocetakTermina());
        trening.setKrajTermina(trDto.getKrajTermina());
        trening.setTerminTreninga(trDto.getTerminTreninga());
        trening.setGrupni(trDto.isGrupni());

        return trening;
    }
}
