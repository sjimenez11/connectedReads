package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequestDto {
    private final String isbn;
    private final String title;
    private final String author;
    private final Double price;
    //portada
    private final String genre;
    private final String synopsis;
    private final Long stock;
}
