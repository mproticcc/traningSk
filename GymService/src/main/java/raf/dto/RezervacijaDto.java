package raf.dto;

import lombok.Getter;
import lombok.Setter;
import raf.domain.Trening;
@Getter
@Setter
public class RezervacijaDto {

    private Long rezervacija_id;

    //private int brPrijavljenih;

    private Trening rezervisaniTrening;

    private int cenaTreninga;

    private Long clientID;

}
