package saraymarcos.ProyectSpringBoot.dtos.user;

import saraymarcos.ProyectSpringBoot.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private Role role;
}

