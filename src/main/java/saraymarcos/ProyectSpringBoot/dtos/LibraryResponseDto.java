package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.models.User;

import java.util.List;

@Data
@AllArgsConstructor
public class LibraryResponseDto {
    private Long id;
    private List<Book> books;
    private User user;
}