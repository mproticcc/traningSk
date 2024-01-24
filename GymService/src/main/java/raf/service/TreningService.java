package raf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.domain.Trening;
import raf.dto.*;

public interface TreningService {

    Page<TreningDto> findAll(Pageable pageable); //read

    TreningDto findById(Long id);

    TreningDto add(TreningCreateDto treningCreateDto);

    TreningDto update(TreningUpdateDto treningUpdateDto); //update

    void deleteById(Long trening_id); //delete

}
