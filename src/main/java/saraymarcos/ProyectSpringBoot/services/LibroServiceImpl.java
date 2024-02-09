package saraymarcos.ProyectSpringBoot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.models.Libro;
import saraymarcos.ProyectSpringBoot.repository.LibroRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LibroServiceImpl implements LibroService{

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro findById(Long id) {
        return libroRepository.findById(id).orElseThrow();
    }

    @Override
    public Libro findByUuid(UUID uuid) {
        return libroRepository.findLibroByUuid(uuid).orElseThrow();
    }

    @Override
    public Libro findLibroByISBN(String ISBN) {
        return libroRepository.findLibroByISBN(ISBN);
    }


    @Override
    public List<Libro> findLibrosByGenero(String genero) {
        return libroRepository.findLibrosByGeneroContainsIgnoreCase(genero);
    }

    @Override
    public List<Libro> findLibrosByTitulo(String titulo) {
        return null;
        //return libroRepository.findLibrosByTituloContainsIgnoreCase(titulo);
    }

    @Override
    public List<Libro> findLibrosByAutor(String autor) {
        return libroRepository.findLibrosByAutorContainsIgnoreCase(autor);
    }

    @Override
    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(Long id, Libro libro) {

        Libro updated = this.findById(id);

        //Actualizamos los datos
        updated.setTitulo(libro.getTitulo());
        updated.setAutor(libro.getAutor());
        updated.setGenero(libro.getGenero());
        updated.setSinopsis(libro.getSinopsis());
        updated.setPrecio(libro.getPrecio());
        updated.setStock(libro.getStock());

        // Guardamos los cambios
        return libroRepository.save(updated);
    }
}
