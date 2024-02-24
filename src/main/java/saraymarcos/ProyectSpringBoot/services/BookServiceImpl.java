package saraymarcos.ProyectSpringBoot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.repositories.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
  public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

//    @Override
//    public Book findByUuid(UUID uuid) {
//        return bookRepository.findLibroByUuid(uuid).orElseThrow();
//    }

//    @Override
//    public Book findLibroByISBN(String isbn) {
//        return bookRepository.findLibroByISBN(isbn);
//    }


    @Override
    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.findBooksByGenreContainsIgnoreCase(genre);
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return null;
        //return libroRepository.findLibrosByTituloContainsIgnoreCase(titulo);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthorContainsIgnoreCase(author);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {

        Book updated = this.findById(id);

        //Actualizamos los datos
        updated.setTitle(book.getTitle());
        updated.setAuthor(book.getAuthor());
        updated.setGenre(book.getGenre());
        updated.setSynopsis(book.getSynopsis());
        updated.setPrice(book.getPrice());
        updated.setStock(book.getStock());

        // Guardamos los cambios
        return bookRepository.save(updated);
    }
}
