package saraymarcos.ProyectSpringBoot.mappers;

import  saraymarcos.ProyectSpringBoot.dtos.user.UserDto;
import  saraymarcos.ProyectSpringBoot.dtos.user.UserDtoRegister;
import saraymarcos.ProyectSpringBoot.models.Role;
import saraymarcos.ProyectSpringBoot.models.User;
import saraymarcos.ProyectSpringBoot.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserMapper {
    public User fromDtoRegister(UserDtoRegister dto, UserRepository repository) {
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent() || !Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            return null;
        }
        else return new User(null, dto.getEmail(), dto.getPassword(), Role.USER, null);
    }

    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getRole(),
                entity.getLibrary()
        );
    }

    public List<UserDto> toDto(List<User> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    public User toModelfromRequestDto(Long categoryId) {
        return new User(
                categoryId,
                null,
                null,
                null,
                null
        );
    }
}


