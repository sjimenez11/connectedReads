package saraymarcos.ProyectSpringBoot.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saraymarcos.ProyectSpringBoot.dtos.LibroRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.LibroResponseDto;
import saraymarcos.ProyectSpringBoot.models.Libro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class LibroMapper {

    public LibroResponseDto toResponse(Libro libro) {
        return new LibroResponseDto(
                libro.getId(),
                libro.getUuid(),
                libro.getISBN(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getPrecio(),
                libro.getGenero(),
                libro.getSinopsis(),
                libro.getStock(),
                libro.getCreatedAt(),
                libro.getUpdatedAt()
        );
    }

    public List<LibroResponseDto> toResponse(List<Libro> libro) {
        return libro.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Libro toModel(LibroRequestDto libroRequestDto) {
        return new Libro(
                0L,
                UUID.randomUUID(),
                libroRequestDto.getISBN(),
                libroRequestDto.getTitulo(),
                libroRequestDto.getAutor(),
                libroRequestDto.getPrecio(),
                libroRequestDto.getGenero(),
                libroRequestDto.getSinopsis(),
                libroRequestDto.getStock(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
