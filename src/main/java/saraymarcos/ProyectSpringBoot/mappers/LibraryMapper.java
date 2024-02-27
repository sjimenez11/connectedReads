package saraymarcos.ProyectSpringBoot.mappers;

import org.springframework.stereotype.Component;
import saraymarcos.ProyectSpringBoot.dtos.LibraryRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.LibraryResponseDto;
import saraymarcos.ProyectSpringBoot.models.Library;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LibraryMapper {

    public LibraryResponseDto toResponse(Library library) {
        return new LibraryResponseDto(
                library.getId(),
                library.getBooks()
        );
    }

    public List<LibraryResponseDto> toResponse(List<Library> library) {
        return library.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Library toModel(LibraryRequestDto libraryRequestDto) {
        return new Library(
                0L,
                libraryRequestDto.getBooks()
        );
    }
}
