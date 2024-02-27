package saraymarcos.ProyectSpringBoot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.models.Book;
import saraymarcos.ProyectSpringBoot.models.Library;
import saraymarcos.ProyectSpringBoot.repositories.LibraryRepository;

import java.util.List;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService{

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library findById(Long id) {
        return libraryRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public Library save(Library library) {
        return libraryRepository.save(library);
    }

    @Override
    public Library update(Long id, Library library) {

        Library updated = this.findById(id);

        //Actualizamos los datos
        updated.setBooks(library.getBooks());

        // Guardamos los cambios
        return libraryRepository.save(updated);
    }
}
