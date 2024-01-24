package com.Notifications.dto;

import com.Notifications.domain.TipNotifikacije;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class NotifikacijaDto {
    private Long id;
    private TipNotifikacije tipNotifikacije;
    private LocalDateTime vremeSlanja;

    private String tekst;

    private String link;

    private Long clinetID;


}
