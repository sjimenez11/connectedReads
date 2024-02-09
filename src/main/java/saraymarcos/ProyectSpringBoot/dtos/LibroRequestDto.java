package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibroRequestDto {
    private final Long ISBN;
    private final String titulo;
    private final String autor;
    private final Double precio;
    //portada
    private final String genero;
    private final String sinopsis;
    private final Long stock;
}
