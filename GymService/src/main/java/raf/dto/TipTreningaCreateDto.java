package raf.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipTreningaCreateDto {
    @NotBlank
    private String nazivTipa;

}
