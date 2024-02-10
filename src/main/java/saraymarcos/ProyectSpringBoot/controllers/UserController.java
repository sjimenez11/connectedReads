package saraymarcos.ProyectSpringBoot.controllers;

import saraymarcos.ProyectSpringBoot.config.jwt.JwtTokenUtils;
import saraymarcos.ProyectSpringBoot.dtos.user.*;
import saraymarcos.ProyectSpringBoot.errors.UserException;
import saraymarcos.ProyectSpringBoot.mappers.UserMapper;
import saraymarcos.ProyectSpringBoot.models.Role;
import saraymarcos.ProyectSpringBoot.models.User;
import saraymarcos.ProyectSpringBoot.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connectedReads/users")
@Slf4j
public class UserController {
    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserMapper mapper;

    @Autowired public UserController(UserService service, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserMapper mapper) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.mapper = mapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAll(
            @AuthenticationPrincipal User user,
            @RequestParam @Nullable String role
    ) {
        try {
            if (role != null  && !role.isEmpty()) {
                log.info("find all users by role " + role);
                return ResponseEntity.ok(service.findUsersByRole(Role.valueOf(role)));
            }
            log.info("find all users");
            return ResponseEntity.ok(service.findAll());
        }
        catch (Exception e) {
            throw new UserException.UserBadRequestException(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findByEmail(
            @AuthenticationPrincipal User user,
            @PathVariable String email
    ) {
        log.info("find by email " + email);
        return ResponseEntity.ok(service.findUserByEmail(email));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        log.info("find by ID " + id);
        return ResponseEntity.ok(service.findUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDtoWithToken> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UserDtoCreate dto
    ) {
        return ResponseEntity.created(null).body(service.create(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoWithToken> register(
            @RequestBody UserDtoRegister dto
    ) {
        return ResponseEntity.created(null).body(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDtoWithToken> login(@Valid @RequestBody UserDtoLogin dto) {
        //dto.validate();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new UserDtoWithToken(mapper.toDto(user), jwtTokenUtils.create(user)));
    }

    @PutMapping("/update/me")
    public ResponseEntity<UserDto> updateMySelf(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UserDtoUpdate dto
    ) {
        log.info("Updating self");
        //dto.validate();
        return ResponseEntity.ok(service.updateSelf(user.getId(), dto));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UserDtoUpdate dto
    ) {
        log.info("Updating");
        //dto.validate();
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<UserDto> delete(
            @PathVariable String email,
            @AuthenticationPrincipal User user
    ) {
        log.info("Deleting");
        return ResponseEntity.ok(service.delete(email));
    }
}