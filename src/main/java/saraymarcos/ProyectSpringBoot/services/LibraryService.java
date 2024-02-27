package saraymarcos.ProyectSpringBoot.services;

import saraymarcos.ProyectSpringBoot.models.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    List<Library> findAll();
    Library findById(Long id);
    void deleteById(Long id);
    Library save(Library book);
    Library update(Long id, Library book);
}
