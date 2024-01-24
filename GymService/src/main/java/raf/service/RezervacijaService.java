package raf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.domain.Trening;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;


public interface RezervacijaService {
    Page<RezervacijaDto> findAll(Pageable pageable); //read

    Page<RezervacijaDto> findByClientId(Pageable pageable,Long client_id);

    RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto); //create

    RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto);

    void deleteByRezervisaniTrening(Trening trening);

    void deleteById(Long id); //delete

    void deleteByIdClient(Long id,Long resid); //delete
}
