package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.User;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadingGroupResponseDto {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private List<User> users;
}
