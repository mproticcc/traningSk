package raf.domain;

import com.User.domain.Manager;
import com.User.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "sale")
public class FiskulturnaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sala_id;

    private String naziv;

    private int kapacitet;

    private int loyalty;


    private Long manager_id;


    private String opis;

    private int brojTrenera;
}
