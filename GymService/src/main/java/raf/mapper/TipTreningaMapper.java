package raf.mapper;

import org.springframework.stereotype.Component;
import raf.domain.TipTreninga;
import raf.dto.TipTreningaCreateDto;
import raf.dto.TipTreningaDto;

@Component
public class TipTreningaMapper {

    public TipTreningaDto DomainToDtoObject(TipTreninga tt){
        TipTreningaDto tDto = new TipTreningaDto();
        tDto.setId(tt.getTipTreninga_id());
        tDto.setNazivTipa(tt.getNazivTipa());

        return tDto;
    }

    public TipTreninga DtoObjectToDomain(TipTreningaCreateDto tDto){
        TipTreninga tt = new TipTreninga();
        tt.setNazivTipa(tDto.getNazivTipa());

        return tt;
    }
}
