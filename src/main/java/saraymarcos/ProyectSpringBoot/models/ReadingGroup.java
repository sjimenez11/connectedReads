package saraymarcos.ProyectSpringBoot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//al crear la base de datos, la tabla será en minúsculas y las mayúsculas de las clases serán representadas con un barrabaja (_)
//reading_group
public class ReadingGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String genre;

    //TODO: añadir idUsuarioCreador
    //TODO: cambiar el genero a List
}
