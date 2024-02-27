package saraymarcos.ProyectSpringBoot.mappers;

import org.springframework.stereotype.Component;
import saraymarcos.ProyectSpringBoot.dtos.ReadingGroupRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.ReadingGroupResponseDto;
import saraymarcos.ProyectSpringBoot.models.ReadingGroup;

import java.util.List;

@Component
public class ReadingGroupMapper {
    public ReadingGroupResponseDto toResponse(ReadingGroup readingGroup){
        return new ReadingGroupResponseDto(
                readingGroup.getId(),
                readingGroup.getName(),
                readingGroup.getDescription(),
                readingGroup.getGenre(),
                readingGroup.getUsers()
        );
    }

    public List<ReadingGroupResponseDto> toResponse(List<ReadingGroup> readingGroups){
        return readingGroups.stream()
                .map(this::toResponse)
                .toList();
    }

    public ReadingGroup toModel(ReadingGroupRequestDto readingGroupRequestDto){
        return new ReadingGroup(
                0L,
                readingGroupRequestDto.getName(),
                readingGroupRequestDto.getDescription(),
                readingGroupRequestDto.getGenre(),
                readingGroupRequestDto.getUsers()
        );
    }
}
