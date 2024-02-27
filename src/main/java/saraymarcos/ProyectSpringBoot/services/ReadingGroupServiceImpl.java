package saraymarcos.ProyectSpringBoot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraymarcos.ProyectSpringBoot.models.ReadingGroup;
import saraymarcos.ProyectSpringBoot.repositories.ReadingGroupRepository;

import java.util.List;

@Service
@Slf4j
public class ReadingGroupServiceImpl implements ReadingGroupService {
    private final ReadingGroupRepository readingGroupRepository;

    @Autowired
    public ReadingGroupServiceImpl(ReadingGroupRepository readingGroupRepository){
        this.readingGroupRepository = readingGroupRepository;
    }

    @Override
    public List<ReadingGroup> findAll() {
        return readingGroupRepository.findAll();
    }

    @Override
    public ReadingGroup findById(Long id) {
        return readingGroupRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ReadingGroup> findReadingGroupsByName(String name) {
        return readingGroupRepository.findGroupByName(name);
    }

    @Override
    public List<ReadingGroup> findReadingGroupsByNameContainsIgnoreCase(String name) {
        return readingGroupRepository.findReadingGroupsByNameContainsIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        readingGroupRepository.deleteById(id);
    }

    @Override
    public ReadingGroup save(ReadingGroup readingGroup) {
        return readingGroupRepository.save(readingGroup);
    }

    @Override
    public ReadingGroup update(Long id, ReadingGroup readingGroup) {
        ReadingGroup updated = this.findById(id);
        //actualización de datos
        updated.setName(readingGroup.getName());
        updated.setDescription(readingGroup.getDescription());
        updated.setGenre(readingGroup.getGenre());
        //guardado de cambios
        return readingGroupRepository.save(updated);
    }
}
