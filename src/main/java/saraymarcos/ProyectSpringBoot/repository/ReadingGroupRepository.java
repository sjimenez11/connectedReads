package saraymarcos.ProyectSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saraymarcos.ProyectSpringBoot.models.ReadingGroup;

import java.util.List;

@Repository
public interface ReadingGroupRepository extends JpaRepository<ReadingGroup, Long> {
    List<ReadingGroup> findGroupByName(String name);
    List<ReadingGroup> findGroupByGenre(String genre);
}
