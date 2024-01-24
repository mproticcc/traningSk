package raf.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import raf.domain.FiskulturnaSala;
import raf.domain.Rezervacija;
import raf.domain.TipTreninga;
import raf.domain.Trening;
import raf.dto.TreningCreateDto;
import raf.dto.TreningDto;
import raf.dto.TreningUpdateDto;
import raf.listener.helper.MessageHelper;
import raf.mapper.TreningMapper;
import raf.repository.FiskulturnaSalaRepository;
import raf.repository.RezervacijaRepository;
import raf.repository.TipTreningaRepository;
import raf.repository.TreningRepository;
import raf.service.RezervacijaService;
import raf.service.TreningService;

@Service
@AllArgsConstructor
public class TreningServiceImpl implements TreningService {
    private RezervacijaRepository rezervacijaRepository;
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private TipTreningaRepository tipTreningaRepository;

    private MessageHelper messageHelper;

    private TreningRepository treningRepository;

    @Value("${destination.cancelledTrainingManager}")
    private String destination2;

    private TreningMapper treningMapper;

    private JmsTemplate jmsTemplate;
    @Lazy
    private RezervacijaService rezervacijaService;

    @Override
    public Page<TreningDto> findAll(Pageable pageable) {

        return treningRepository.findAll(pageable).map(treningMapper::DomainObjectToDto);
    }

    @Override
    public TreningDto findById(Long id) {
        Trening trening = treningRepository.findById(id).orElseThrow(()->new RuntimeException());


        return treningMapper.DomainObjectToDto(trening);
    }


    @Override
    public TreningDto add(TreningCreateDto treningCreateDto) {

        FiskulturnaSala fs = fiskulturnaSalaRepository.findById(treningCreateDto.getSala_id()).orElseThrow(RuntimeException::new);
        TipTreninga tt  = tipTreningaRepository.findById(treningCreateDto.getTip_id()).orElseThrow(RuntimeException::new);
        treningCreateDto.setSala(fs);
        treningCreateDto.setTip(tt);

        Trening trening = treningMapper.DtoToDomainObject(treningCreateDto);
        treningRepository.save(trening);

        TreningDto treningDto = treningMapper.DomainObjectToDto(trening);

        return treningDto;
    }


    @Override
    public TreningDto update(TreningUpdateDto treningUpdateDto) {
        Trening trening = treningRepository.findById(treningUpdateDto.getId()).orElseThrow(()-> new RuntimeException());
        if(treningUpdateDto.getCenaTreninga() != 0)
         trening.setCenaTreninga(treningUpdateDto.getCenaTreninga());

        trening.setGrupni(treningUpdateDto.isGrupni());
        if(treningUpdateDto.getDatum() != null)
         trening.setTerminTreninga(treningUpdateDto.getDatum());

        if(treningUpdateDto.getPocetakTermina() != null)
         trening.setPocetakTermina(treningUpdateDto.getPocetakTermina());

        if(treningUpdateDto.getKrajTermina() != null)
         trening.setKrajTermina(treningUpdateDto.getKrajTermina());

        treningRepository.save(trening);

        return treningMapper.DomainObjectToDto(trening);
    }

    @Override
    public void deleteById(Long trening_id) {
        Trening trening = treningRepository.findById(trening_id).orElseThrow(RuntimeException::new);

        rezervacijaService.deleteByRezervisaniTrening(trening);

        jmsTemplate.convertAndSend(destination2,messageHelper.createTextMessage(trening.getSala().getManager_id()));

        treningRepository.deleteById(trening_id);
    }
}
