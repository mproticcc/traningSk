package raf.mapper;

import com.User.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import raf.domain.FiskulturnaSala;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FiskulturnaSalaMapper {

    public FiskulturnaSalaDto DomainObjectToDto(FiskulturnaSala fs){
        FiskulturnaSalaDto fsDto = new FiskulturnaSalaDto();
        fsDto.setId(fs.getSala_id());
        fsDto.setNaziv(fs.getNaziv());
        fsDto.setKapacitet(fs.getKapacitet());
        fsDto.setBrojTrenera(fs.getBrojTrenera());
        fsDto.setOpis(fs.getOpis());
        fsDto.setManager_id(fs.getManager_id());
        fsDto.setLoyalty(fs.getLoyalty());
        return fsDto;
    }

    public FiskulturnaSala DtoToDomainObject(FiskulturnaSalaCreateDto fsDto){

        FiskulturnaSala fs = new FiskulturnaSala();
        fs.setNaziv(fsDto.getName());
        fs.setOpis(fsDto.getOpis());
        if(fsDto.getKapacitet() != 0)
        fs.setKapacitet(fsDto.getKapacitet());
        if(fsDto.getBrojTrenera() != 0)
        fs.setBrojTrenera(fsDto.getBrojTrenera());
        if(fsDto.getManager_id() != 0)
        fs.setManager_id(fsDto.getManager_id());
        if(fsDto.getLoyalty() != 0)
            fs.setLoyalty(fsDto.getLoyalty());
        return fs;
    }

}
