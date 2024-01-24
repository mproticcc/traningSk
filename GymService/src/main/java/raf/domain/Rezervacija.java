package raf.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name="rezervacije")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rezervacija_id;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    @JoinColumn(name="rezervisaniTrening")
    private Trening rezervisaniTrening;

    private int cenaTreninga ;

    private Long clientID;

}
