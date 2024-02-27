package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.Library;

import java.util.List;

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
    private final String classification;
    private final Long stock;
    private final List<Library> libraries;
}
