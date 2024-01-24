package raf.dto;



import com.User.domain.Manager;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class FiskulturnaSalaCreateDto {


    private String name;

    private int kapacitet;

    private String opis;

    private int brojTrenera;
    private int loyalty;


    private Long manager_id;

}
