package saraymarcos.ProyectSpringBoot.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saraymarcos.ProyectSpringBoot.dtos.LibraryRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.LibraryResponseDto;
import saraymarcos.ProyectSpringBoot.models.Library;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LibraryMapper {

    private final UserMapper userMapper;
    @Autowired
    public LibraryMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    public LibraryResponseDto toResponse(Library library) {
        return new LibraryResponseDto(
                library.getId(),
                library.getBooks(),
                library.getUser()
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
                libraryRequestDto.getBooks(),
                libraryRequestDto.getUserId() != null ?
                        userMapper.toModelfromRequestDto(libraryRequestDto.getUserId()) : null
                );
    }
}
