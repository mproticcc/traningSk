package raf.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipoviTreninga")
public class TipTreninga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipTreninga_id;

    private String nazivTipa; //kalistenika,gym,joga,boks,mma itd.

}
