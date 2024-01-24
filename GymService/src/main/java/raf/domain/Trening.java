package raf.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="treninzi")
public class Trening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trening_id;

    @ManyToOne
    @JoinColumn(name="salaOdrzavanja")
    private FiskulturnaSala sala;

    @ManyToOne
    @JoinColumn(name = "tipTreninga")
    private TipTreninga tip;

    private int brRezervacija=0;

    private int cenaTreninga;
    @NotNull
    private boolean grupni = true;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate terminTreninga;

    private LocalTime pocetakTermina;

    private LocalTime krajTermina;
}
