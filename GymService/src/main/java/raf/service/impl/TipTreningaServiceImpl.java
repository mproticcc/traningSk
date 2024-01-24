package raf.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.domain.TipTreninga;
import raf.dto.TipTreningaCreateDto;
import raf.dto.TipTreningaDto;
import raf.dto.TipTreningaUpdateDto;
import raf.mapper.TipTreningaMapper;
import raf.repository.TipTreningaRepository;
import raf.service.TipTreningaService;

@Service
@AllArgsConstructor
public class TipTreningaServiceImpl implements TipTreningaService {

    private TipTreningaMapper ttMapper;

    private TipTreningaRepository ttRepository;


    @Override
    public Page<TipTreningaDto> findAll(Pageable pageable) {
        ttRepository.findAll(pageable).map(ttMapper::DomainToDtoObject);
        return null;
    }

    @Override
    public TipTreningaDto add(TipTreningaCreateDto ttC) {
        TipTreninga tt = ttMapper.DtoObjectToDomain(ttC);
        ttRepository.save(tt);

        return ttMapper.DomainToDtoObject(tt);
    }

    @Override
    public TipTreningaDto update(TipTreningaUpdateDto ttDto) {
        TipTreninga tt = ttRepository.findById(ttDto.getId()).orElseThrow(()-> new RuntimeException());
        tt.setNazivTipa(ttDto.getNaziv());
        ttRepository.save(tt);
        return ttMapper.DomainToDtoObject(tt);
    }

    @Override
    public void deleteById(Long id) {
        ttRepository.deleteById(id);
    }
}
