package saraymarcos.ProyectSpringBoot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saraymarcos.ProyectSpringBoot.dtos.user.UserDto;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Book> books;
    @OneToOne
    private User user;
}
