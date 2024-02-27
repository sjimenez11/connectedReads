package saraymarcos.ProyectSpringBoot.mappers;

import org.springframework.stereotype.Component;
import saraymarcos.ProyectSpringBoot.dtos.BookRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.BookResponseDto;
import saraymarcos.ProyectSpringBoot.models.Book;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookMapper {

    public BookResponseDto toResponse(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getGenre(),
                book.getSynopsis(),
                book.getClassification(),
                book.getStock(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    public List<BookResponseDto> toResponse(List<Book> book) {
        return book.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Book toModel(BookRequestDto bookRequestDto) {
        return new Book(
                0L,
                bookRequestDto.getIsbn(),
                bookRequestDto.getTitle(),
                bookRequestDto.getAuthor(),
                bookRequestDto.getPrice(),
                bookRequestDto.getGenre(),
                bookRequestDto.getSynopsis(),
                bookRequestDto.getClassification(),
                bookRequestDto.getStock(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
