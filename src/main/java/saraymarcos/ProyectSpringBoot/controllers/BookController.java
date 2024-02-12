package saraymarcos.ProyectSpringBoot.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.BookRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.BookResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.BookMapper;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.services.BookService;
import saraymarcos.ProyectSpringBoot.services.BookServiceImpl;

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

        return ResponseEntity.ok(
                bookMapper.toResponse(bookService.findAll())
        );
    }

    @GetMapping("/by-genre/{genre}")
    public ResponseEntity<List<BookResponseDto>> getBooksByGenre(
            @PathVariable String genre
    ) {
        log.info("getBooksByGenre");

        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByGenre(genre))
        );
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(
            @PathVariable String title
    ) {
        log.info("getBooksByTitle");

        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByTitle(title))
        );
    }

    @GetMapping("/by-author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(
            @PathVariable String author
    ) {
        log.info("getBooksByAuthor");

        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByAuthor(author))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(
            @PathVariable Long id
    ) {
        log.info("getBookById");
        return ResponseEntity.ok(
                bookMapper.toResponse(bookService.findById(id))
        );
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
/*
    @GetMapping("/{ISBN}")
    public ResponseEntity<BookResponseDto> getBookByISBN(
            @PathVariable String ISBN
    ) {
        log.info("getBookByISBN");

        return ResponseEntity.ok(BookMapper.toResponse(Bookservice.findBookByISBN(ISBN))
        );
    }
*/
    @PostMapping
    public ResponseEntity<BookResponseDto> postBook(
            @RequestBody BookRequestDto bookRequestDto
    ) {
        log.info("addBook");
        Book bookSaved = bookService.save(bookMapper.toModel(bookRequestDto));
        return ResponseEntity.created(null).body(
                bookMapper.toResponse(bookSaved)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> putBook(
            @PathVariable Long id,
            @RequestBody BookRequestDto bookRequestDto
    ) {
        log.info("putBook");
        Book bookUpdated = bookService.update(id, bookMapper.toModel(bookRequestDto));
        return ResponseEntity.ok(
                bookMapper.toResponse(bookUpdated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(
            @PathVariable Long id
    ) {
        log.info("deleteBook");
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
