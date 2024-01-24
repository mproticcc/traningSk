package raf.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
public class TreningUpdateDto {

    private Long id;

    private int cenaTreninga;

    private LocalDate datum;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;

    private boolean grupni = false;

}
