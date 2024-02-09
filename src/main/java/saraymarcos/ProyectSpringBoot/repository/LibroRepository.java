package saraymarcos.ProyectSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saraymarcos.ProyectSpringBoot.models.Libro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findLibroByUuid(UUID uuid);
    Libro findLibrosByISBNContainsIgnoreCase(String ISBN);
    List<Libro> findLibrosByAutorContainsIgnoreCase(String autor);
    List<Libro> findLibrosByGeneroContainsIgnoreCase(String genero);
    List<Libro> findLibrosByTituloContainsIgnoreCase(String titulo);
}
