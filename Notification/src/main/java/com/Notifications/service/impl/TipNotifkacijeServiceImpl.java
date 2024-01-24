package com.Notifications.service.impl;

import com.Notifications.domain.TipNotifikacije;
import com.Notifications.dto.TipNotifikacijeCreateDto;
import com.Notifications.dto.TipNotifikacijeDto;
import com.Notifications.dto.TipNotifikacijeUpdateDto;
import com.Notifications.mapper.TipNotifikacijeMapper;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.TipNotifikacijeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
@Service
public class TipNotifkacijeServiceImpl implements TipNotifikacijeService {

    NotificationTypeRepository notificationTypeRepository;

    TipNotifikacijeMapper tipNotifikacijeMapper;

    public TipNotifkacijeServiceImpl(NotificationTypeRepository notificationTypeRepository, TipNotifikacijeMapper tipNotifikacijeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.tipNotifikacijeMapper = tipNotifikacijeMapper;
    }

    @Override
    public TipNotifikacijeDto add(TipNotifikacijeCreateDto tipNotifikacijeCreateDto) {
        TipNotifikacije tipNotifikacije = tipNotifikacijeMapper.tipNotifikacijeCreateDto(tipNotifikacijeCreateDto);
        notificationTypeRepository.save(tipNotifikacije);
        return tipNotifikacijeMapper.tipNotifikacijeDto(tipNotifikacije);
    }

    @Override
    public Page<TipNotifikacijeDto> findAll(org.springframework.data.domain.Pageable pageable) {
        return notificationTypeRepository.findAll(pageable).map(tipNotifikacijeMapper::tipNotifikacijeDto);
    }

    @Override
    public void delete(Long id) {
        notificationTypeRepository.deleteById(id);
    }

    @Override
    public void update(Long id,TipNotifikacijeUpdateDto tipNotifikacijeUpdateDto) {
        TipNotifikacije tipNotifikacije = notificationTypeRepository.findById(id).orElseThrow(() -> new RuntimeException());
        tipNotifikacije.setNaziv(tipNotifikacijeUpdateDto.getNaziv());

        notificationTypeRepository.save(tipNotifikacije);
    }
}
