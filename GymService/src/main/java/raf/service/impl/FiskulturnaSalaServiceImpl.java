package raf.service.impl;

import com.User.domain.Manager;
import com.User.exception.NotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raf.domain.FiskulturnaSala;
import raf.dto.FiskulturnaSalaCreateDto;
import raf.dto.FiskulturnaSalaDto;
import raf.dto.FiskulturnaSalaUpdateDto;
import raf.mapper.FiskulturnaSalaMapper;
import raf.repository.FiskulturnaSalaRepository;
import raf.service.FiskulturnaSalaService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.userservice.ManagerDto;

@Service
@AllArgsConstructor
public class FiskulturnaSalaServiceImpl implements FiskulturnaSalaService {

    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private FiskulturnaSalaMapper fiskulturnaSalaMapper;

    private RestTemplate userServiceRestTemplate;

    @Override
    public Page<FiskulturnaSalaDto> findAll(Pageable pageable) {
        return fiskulturnaSalaRepository.findAll(pageable).map(fiskulturnaSalaMapper::DomainObjectToDto);
    }

    @Override
    public FiskulturnaSalaDto addSala(FiskulturnaSalaCreateDto fiskulturnaSalaCreateDto) {
        ResponseEntity<ManagerDto> managerDtoResponseEntity = null;
        try{
            managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/", HttpMethod.GET,null, ManagerDto.class);
        }
        catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Manager with id: %d not found. ",fiskulturnaSalaCreateDto.getManager_id()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        fiskulturnaSalaCreateDto.setManager_id(managerDtoResponseEntity.getBody().getId());
        FiskulturnaSala fs = fiskulturnaSalaMapper.DtoToDomainObject(fiskulturnaSalaCreateDto);
        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);
    }

    @Override
    public FiskulturnaSalaDto update(Long id, FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(id).orElseThrow(RuntimeException::new);

        if(fiskulturnaSalaUpdateDto.getNaziv()!=null)
        fs.setNaziv(fiskulturnaSalaUpdateDto.getNaziv());
        fs.setOpis(fiskulturnaSalaUpdateDto.getOpis());
        if(fiskulturnaSalaUpdateDto.getKapacitet()!= 0)
        fs.setKapacitet(fiskulturnaSalaUpdateDto.getKapacitet());
        if(fiskulturnaSalaUpdateDto.getBrojTrenera() != 0)
        fs.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrojTrenera());
        if(fiskulturnaSalaUpdateDto.getLoyalty() != 0)
            fs.setLoyalty(fiskulturnaSalaUpdateDto.getLoyalty());

        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);
    }

    @Override
    public void deleteById(Long id) {
        fiskulturnaSalaRepository.deleteById(id);
    }

    @Override
    public FiskulturnaSalaDto updateManager(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(fiskulturnaSalaUpdateDto.getSala_id()).orElseThrow(()-> new RuntimeException());

        if(fiskulturnaSalaUpdateDto.getBrojTrenera() != 0)
            fs.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrojTrenera());
        if(fiskulturnaSalaUpdateDto.getKapacitet()!=0)
            fs.setKapacitet(fiskulturnaSalaUpdateDto.getKapacitet());
        fiskulturnaSalaRepository.save(fs);

        return fiskulturnaSalaMapper.DomainObjectToDto(fs);
    }
}
