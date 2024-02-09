package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LibroResponseDto {
    private Long id;
    private UUID uuid;
    private String ISBN;
    private String titulo;
    private String autor;
    private Double precio;
    //portada
    private String genero;
    private String sinopsis;
    private Long stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
