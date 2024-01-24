package raf.dto;

import lombok.Getter;
import lombok.Setter;
import raf.domain.FiskulturnaSala;
import raf.domain.TipTreninga;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class TreningDto {

    private Long id;

    private FiskulturnaSala sala;

    private TipTreninga tip;

    private int cenaTreninga;

    private int brRezervacija;

    private boolean grupni;

    private LocalDate terminTreninga;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;
}
