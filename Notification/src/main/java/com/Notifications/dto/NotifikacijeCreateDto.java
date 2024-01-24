package com.Notifications.dto;

import com.Notifications.domain.TipNotifikacije;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class NotifikacijeCreateDto {
    @NotNull
    private TipNotifikacije tipNotifikacije;
    @NotNull
    private LocalDateTime vremeSlanja;
    @NotNull
    private String tekst;
    @NotNull
    private String link;

    private Long clientId;


}
