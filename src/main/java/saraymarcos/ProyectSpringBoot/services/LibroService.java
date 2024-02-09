package saraymarcos.ProyectSpringBoot.services;

import saraymarcos.ProyectSpringBoot.models.Libro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LibroService{

    List<Libro> findAll();
    Libro findById(Long id);
    Libro findByUuid(UUID uuuuid);
    Libro findLibroByISBN(Long ISBN);
    List<Libro> findLibrosByGenero(String genero);
    List<Libro> findLibrosByTitulo(String titulo);
    List<Libro> findLibrosByAutor(String autor);
    void deleteById(Long id);
    Libro save(Libro libro);
    Libro update(Long id, Libro libro);
    //List<Libro> findByStockLessThanEqual(Double lowStock);
}