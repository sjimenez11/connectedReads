package saraymarcos.ProyectSpringBoot.services;

import saraymarcos.ProyectSpringBoot.models.ReadingGroup;

import java.util.List;

public interface ReadingGroupService {
    List<ReadingGroup> findAll();
    ReadingGroup findById(Long id);
    List<ReadingGroup> findReadingGroupsByName(String name);
    List<ReadingGroup> findReadingGroupsByNameContainsIgnoreCase(String name);
    //TODO: findGrupoByCreador
    void deleteById(Long id);
    ReadingGroup save(ReadingGroup readingGroup);
    ReadingGroup update(Long id, ReadingGroup readingGroup);
}
