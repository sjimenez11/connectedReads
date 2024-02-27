package saraymarcos.ProyectSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saraymarcos.ProyectSpringBoot.models.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
