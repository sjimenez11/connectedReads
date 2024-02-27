package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.Book;

import java.util.List;

@Data
@AllArgsConstructor
public class LibraryRequestDto {
    private final List<Book> books;
    private final Long userId;
}
