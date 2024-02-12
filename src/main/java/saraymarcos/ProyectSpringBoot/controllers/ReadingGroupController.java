package saraymarcos.ProyectSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.ReadingGroupRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.ReadingGroupResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.ReadingGroupMapper;
import saraymarcos.ProyectSpringBoot.models.ReadingGroup;
import saraymarcos.ProyectSpringBoot.services.ReadingGroupService;

import java.util.List;

@RestController
@RequestMapping("/connectedReads/readingGroups")
public class ReadingGroupController {
    private final ReadingGroupService readingGroupService;
    private final ReadingGroupMapper readingGroupMapper;

    @Autowired
    public ReadingGroupController(ReadingGroupService readingGroupService, ReadingGroupMapper readingGroupMapper){
        this.readingGroupService = readingGroupService;
        this.readingGroupMapper = readingGroupMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<ReadingGroupResponseDto>> getAllReadingGroups(){
        return ResponseEntity.ok(
                readingGroupMapper.toResponse(readingGroupService.findAll())
        );
    }

    @GetMapping("/by-nombre/{name}")
    public ResponseEntity<List<ReadingGroupResponseDto>> getReadingGroupsByName(
            @PathVariable String name
    ){
        return ResponseEntity.ok(readingGroupMapper.toResponse(readingGroupService.findReadingGroupsByName(name)));
    }

    @PostMapping("/create")
    public ResponseEntity<ReadingGroupResponseDto> postReadingGroup(
            @RequestBody ReadingGroupRequestDto readingGroupRequestDto
    ){
        ReadingGroup readingGroupSaved = readingGroupService.save(readingGroupMapper.toModel(readingGroupRequestDto));
        return ResponseEntity.created(null).body(
                readingGroupMapper.toResponse(readingGroupSaved)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingGroupResponseDto> putReadingGroup(
            @PathVariable Long id,
            @RequestBody ReadingGroupRequestDto readingGroupRequestDto
    ){
        ReadingGroup readingGroupUpdated = readingGroupService.update(id, readingGroupMapper.toModel(readingGroupRequestDto));
        return ResponseEntity.ok(
                readingGroupMapper.toResponse(readingGroupUpdated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReadingGroupResponseDto> deleteReadingGroup(
            @PathVariable Long id
    ){
        readingGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
