package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.Library;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private Double price;
    //portada
    private String genre;
    private String synopsis;
    private String classification;
    private Long stock;
    private List<Library> libraries;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
