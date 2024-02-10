package saraymarcos.ProyectSpringBoot.repositories;

import saraymarcos.ProyectSpringBoot.models.Role;
import saraymarcos.ProyectSpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(Role role);
    Optional<User> findByUserName(String userName);
}
