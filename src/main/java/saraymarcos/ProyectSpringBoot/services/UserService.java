package saraymarcos.ProyectSpringBoot.services;

import saraymarcos.ProyectSpringBoot.config.jwt.JwtTokenUtils;
import saraymarcos.ProyectSpringBoot.dtos.user.*;
import saraymarcos.ProyectSpringBoot.errors.UserException;
import saraymarcos.ProyectSpringBoot.mappers.UserMapper;
import saraymarcos.ProyectSpringBoot.models.Role;
import saraymarcos.ProyectSpringBoot.models.User;
import saraymarcos.ProyectSpringBoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtTokenUtils tokenUtils;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(UserRepository repository, JwtTokenUtils tokenUtils, UserMapper mapper) {
        this.repository = repository;
        this.tokenUtils = tokenUtils;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + username + " not found."));
    }

    public UserDtoWithToken register(UserDtoRegister dto) {
        if (!Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            throw new UserException.UserBadRequestException(
                    "Password and repeated password do not match.");
        }
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent()) {
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email.");
        }

        User saved = repository.save(new User(null, dto.getEmail(), encoder.encode(dto.getPassword()), Role.USER, null));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                tokenUtils.create(saved)
        );
    }

    public UserDtoWithToken create(UserDtoCreate dto) {
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent()) {
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email.");
        }

        User saved = repository.save(new User(null, dto.getEmail(), encoder.encode(dto.getPassword()), dto.getRole(), dto.getLibrary()));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                tokenUtils.create(saved)
        );
    }

    public List<UserDto> findAll() {
        return mapper.toDto(repository.findAll());
    }
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public List<UserDto> findUsersByRole(Role role) {
        return mapper.toDto(repository.findAllByRole(role));
    }

    public UserDto findUserByEmail(String email) {
        User user = repository.findByUserName(email)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + email + " not found."));
        return mapper.toDto(user);
    }

    public UserDto findUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserException.UserNotFoundException(
                "User with ID " + id + " not found."));
        return mapper.toDto(user);
    }

    public User save(User user){
        return repository.save(user);
    }

    public UserDto updateSelf(Long id, UserDtoUpdate dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with ID " + id + " not found."));

        if (!encoder.matches(dto.getPassword(), user.getUserPassword())) {
            throw new UserException.UserBadRequestException(
                    "Incorrect password.");
        }

        User saved = repository.save(new User(user.getId(), user.getUsername(),
                encoder.encode(dto.getNewPassword()), user.getRole(), user.getLibrary()));
        return mapper.toDto(saved);
    }

    public UserDto update(UserDtoUpdate dto) {
        User user = repository.findByUserName(dto.getEmail())
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + dto.getEmail() + " not found."));

        if (!encoder.matches(dto.getPassword(), user.getUserPassword())) {
            throw new UserException.UserBadRequestException(
                    "Incorrect password.");
        }

        User saved = repository.save(new User(user.getId(), user.getUsername(),
                encoder.encode(dto.getNewPassword()), user.getRole(), user.getLibrary()));
        return mapper.toDto(saved);
    }

    //TODO: Cambiar a que sea por ID.
    public UserDto delete(String email) {
        User user = repository.findByUserName(email)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + email + " not found."));

        repository.deleteById(user.getId());
        return mapper.toDto(user);
    }
}
