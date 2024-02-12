package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
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
    private Long stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
