package saraymarcos.ProyectSpringBoot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import saraymarcos.ProyectSpringBoot.models.User;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadingGroupRequestDto {
    private final String name;
    private final String description;
    private final String genre;
    private final List<User> users;
}
