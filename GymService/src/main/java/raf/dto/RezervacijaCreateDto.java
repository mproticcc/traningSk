package raf.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import raf.domain.Trening;
@Getter
@Setter
public class RezervacijaCreateDto {

    private int cenaTreninga;

    private Trening rezervisaniTrening;

    @NotNull
    private Long clientID;

}
