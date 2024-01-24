package raf.dto;


import lombok.*;

@Getter
@Setter
public class FiskulturnaSalaDto {
    private Long id;

    private String naziv;

    private int kapacitet;

    private String opis;

    private int brojTrenera;

    private int loyalty;

    private Long manager_id;

}
