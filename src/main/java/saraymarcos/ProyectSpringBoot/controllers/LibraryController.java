package saraymarcos.ProyectSpringBoot.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.LibraryRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.LibraryResponseDto;
import saraymarcos.ProyectSpringBoot.dtos.LibraryResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.LibraryMapper;
import saraymarcos.ProyectSpringBoot.models.Library;
import saraymarcos.ProyectSpringBoot.services.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/connectedReads/libraries")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;
    private final LibraryMapper libraryMapper;

    @GetMapping("")
    public ResponseEntity<List<LibraryResponseDto>> getAllLibraries(
    ) {
        log.info("getAllLibraries");
        return ResponseEntity.ok(libraryMapper.toResponse(libraryService.findAll()));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<LibraryResponseDto> getLibraryById(
            @PathVariable Long id
    ) {
        log.info("getLibraryById");
        return ResponseEntity.ok(libraryMapper.toResponse(libraryService.findById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<LibraryResponseDto> postLibrary(
            @RequestBody LibraryRequestDto libraryRequestDto
    ) {
        log.info("addLibrary");
        Library librarySaved = libraryService.save(libraryMapper.toModel(libraryRequestDto));
        return ResponseEntity.created(null).body(libraryMapper.toResponse(librarySaved));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LibraryResponseDto> putLibrary(
            @PathVariable Long id,
            @RequestBody LibraryRequestDto libraryRequestDto
    ) {
        log.info("putLibrary");
        Library libraryUpdated = libraryService.update(id, libraryMapper.toModel(libraryRequestDto));
        return ResponseEntity.ok(libraryMapper.toResponse(libraryUpdated));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<LibraryResponseDto> deleteLibrary(
            @PathVariable Long id
    ) {
        log.info("deleteLibrary");
        libraryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}