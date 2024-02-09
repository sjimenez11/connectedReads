package saraymarcos.ProyectSpringBoot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.LibroResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.LibroMapper;
import saraymarcos.ProyectSpringBoot.services.LibroService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/libros")
@Slf4j
public class LibroController {
    private final LibroService libroService;
    private final LibroMapper libroMapper;

    @Autowired
    public LibroController(LibroService libroService, LibroMapper libroMapper) {
        this.libroService = libroService;
        this.libroMapper = libroMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<LibroResponseDto>> getAllLibros(
    ) {
        log.info("getAllLibros");

        return ResponseEntity.ok(
                libroMapper.toResponse(libroService.findAll())
        );
    }

    @GetMapping("/by-genero/{genero}")
    public ResponseEntity<List<LibroResponseDto>> getLibrosByGenero(
            @PathVariable String genero
    ) {
        log.info("getLibrosByGenero");

        return ResponseEntity.ok(libroMapper.toResponse(libroService.findLibrosByGenero(genero))
        );
    }

    @GetMapping("/by-titulo/{titulo}")
    public ResponseEntity<List<LibroResponseDto>> getLibrosByTitulo(
            @PathVariable String titulo
    ) {
        log.info("getLibrosByTitulo");

        return ResponseEntity.ok(libroMapper.toResponse(libroService.findLibrosByTitulo(titulo))
        );
    }

    @GetMapping("/by-autor/{autor}")
    public ResponseEntity<List<LibroResponseDto>> getLibrosByAutor(
            @PathVariable String autor
    ) {
        log.info("getLibrosByAutor");

        return ResponseEntity.ok(libroMapper.toResponse(libroService.findLibrosByAutor(autor))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseDto> getLibroById(
            @PathVariable Long id
    ) {
        log.info("getLibroById");
        return ResponseEntity.ok(
                libroMapper.toResponse(libroService.findById(id))
        );
    }

    @GetMapping("/find/{uuid}")
    public ResponseEntity<LibroResponseDto> getLibroByUuid(
            @PathVariable UUID uuid
    ) {
        log.info("getLibroByUuid");
        return ResponseEntity.ok(
                libroMapper.toResponse(libroService.findByUuid(uuid))
        );

    }
    /*
    @GetMapping("/{ISBN}")
    public ResponseEntity<LibroResponseDto> getLibroByISBN(
            @PathVariable Long ISBN
    ) {
        log.info("getLibroByISBN");

        return ResponseEntity.ok(libroMapper.toResponse(libroService.findLibroByISBN(ISBN))
        );
    }
*/
    @DeleteMapping("/{id}")
    public ResponseEntity<LibroResponseDto> deleteProduct(
            @PathVariable Long id
    ) {
        log.info("deleteLibro");
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
