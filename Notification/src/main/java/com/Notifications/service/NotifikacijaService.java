package com.Notifications.service;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NotifikacijaService {

    NotifikacijaDto add(NotifikacijeCreateDto notifikacijeCreateDto);

    Page<NotifikacijaDto> findAll(Pageable pageable);

    void posaljiAktivacioniImejl(Notifikacija notifikacija,Long id);

    void posaljiImejlZaPromenuLozinke(Notifikacija notifikacija);

    void posaljiNotifikacijuZakazivanja(Notifikacija notifikacija);

    void posaljiNotifikacijuOtkazivanja(Notifikacija notifikacija);

    void posaljiPodsetnik(Date datumTreninga,Notifikacija notifikacija);

}
