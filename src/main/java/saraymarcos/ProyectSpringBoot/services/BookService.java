package saraymarcos.ProyectSpringBoot.services;


import saraymarcos.ProyectSpringBoot.models.Book;

import java.util.List;
import java.util.UUID;

public interface BookService{

    List<Book> findAll();
    Book findById(Long id);
//    Book findByUuid(UUID uuid);
    Book findBookByIsbn(String isbn);
    List<Book> findBooksByGenre(String genre);
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByClassification(String classification);
    List<Book> findBooksByPriceLessThanEqual(Double price);
    void deleteById(Long id);
    Book save(Book book);
    Book update(Long id, Book book);
    //List<Book> findByStockLessThanEqual(Double lowStock);
}