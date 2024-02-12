package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingGroupResponseDto {
    private Long id;
    private String name;
    private String description;
    private String genre;
}
