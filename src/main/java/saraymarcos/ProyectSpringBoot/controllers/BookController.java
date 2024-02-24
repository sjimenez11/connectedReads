package saraymarcos.ProyectSpringBoot.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.book.BookRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.book.BookResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.BookMapper;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.services.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/connectedReads/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(
    ) {
        log.info("getAllBooks");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findAll()));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookResponseDto>> getBooksByGenre(
            @PathVariable String genre
    ) {
        log.info("getBooksByGenre");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByGenre(genre)));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(
            @PathVariable String title
    ) {
        log.info("getBooksByTitle");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByTitle(title))
        );
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(
            @PathVariable String author
    ) {
        log.info("getBooksByAuthor");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByAuthor(author)));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<BookResponseDto> getBookById(
            @PathVariable Long id
    ) {
        log.info("getBookById");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findById(id)));
    }
/*
    @GetMapping("/find/{uuid}")
    public ResponseEntity<BookResponseDto> getBookByUuid(
            @PathVariable UUID uuid
    ) {
        log.info("getBookByUuid");
        return ResponseEntity.ok(
                bookMapper.toResponse(bookService.findByUuid(uuid))
        );

    }
 */
    @GetMapping("isbn/{isbn}")
    public ResponseEntity<BookResponseDto> getBookByISBN(
            @PathVariable String isbn
    ) {
        log.info("getBookByISBN");
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBookByIsbn(isbn)));
    }
    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> postBook(
            @RequestBody BookRequestDto bookRequestDto
    ) {
        log.info("addBook");
        Book bookSaved = bookService.save(bookMapper.toModel(bookRequestDto));
        return ResponseEntity.created(null).body(bookMapper.toResponse(bookSaved));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<BookResponseDto> putBook(
            @PathVariable Long id,
            @RequestBody BookRequestDto bookRequestDto
    ) {
        log.info("putBook");
        Book bookUpdated = bookService.update(id, bookMapper.toModel(bookRequestDto));
        return ResponseEntity.ok(bookMapper.toResponse(bookUpdated));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(
            @PathVariable Long id
    ) {
        log.info("deleteBook");
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
