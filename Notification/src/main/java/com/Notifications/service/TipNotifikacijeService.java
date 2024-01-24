package com.Notifications.service;

import com.Notifications.dto.TipNotifikacijeCreateDto;
import com.Notifications.dto.TipNotifikacijeDto;
import com.Notifications.dto.TipNotifikacijeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipNotifikacijeService {
    TipNotifikacijeDto add(TipNotifikacijeCreateDto tipNotifikacijeCreateDto);
    Page<TipNotifikacijeDto> findAll(Pageable pageable);

    void delete(Long id);

    void update(Long id, TipNotifikacijeUpdateDto notifikacijeUpdateDto);

}
