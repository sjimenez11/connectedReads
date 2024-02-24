package saraymarcos.ProyectSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saraymarcos.ProyectSpringBoot.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByIsbnContainsIgnoreCase(String isbn);
    List<Book> findBooksByAuthorContainsIgnoreCase(String author);
    List<Book> findBooksByGenreContainsIgnoreCase(String genre);
    List<Book> findBooksByTitleContainsIgnoreCase(String title);
}
