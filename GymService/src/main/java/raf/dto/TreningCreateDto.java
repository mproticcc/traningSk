package raf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import raf.domain.FiskulturnaSala;
import raf.domain.Rezervacija;
import raf.domain.TipTreninga;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class TreningCreateDto {

    @NotNull
    private Long sala_id;
    @NotNull
    private Long tip_id;

    private Long rezervacija_id;

    private FiskulturnaSala sala;

    private TipTreninga tip;
    @NotNull
    private int cenaTreninga;

    private boolean grupni;

    private LocalDate terminTreninga;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;



}
