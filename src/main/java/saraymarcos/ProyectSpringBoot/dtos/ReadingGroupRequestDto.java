package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingGroupRequestDto {
    private final String name;
    private final String description;
    private final String genre;
}
