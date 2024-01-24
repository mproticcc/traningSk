package raf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.domain.TipTreninga;
import raf.dto.TipTreningaCreateDto;
import raf.dto.TipTreningaDto;
import raf.dto.TipTreningaUpdateDto;

public interface TipTreningaService {

    Page<TipTreningaDto> findAll(Pageable pageable);

    TipTreningaDto add(TipTreningaCreateDto ttC);

    TipTreningaDto update(TipTreningaUpdateDto ttDto);

    void deleteById(Long id);

}
