package saraymarcos.ProyectSpringBoot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraymarcos.ProyectSpringBoot.dtos.readingGroup.ReadingGroupRequestDto;
import saraymarcos.ProyectSpringBoot.dtos.readingGroup.ReadingGroupResponseDto;
import saraymarcos.ProyectSpringBoot.mappers.ReadingGroupMapper;
import saraymarcos.ProyectSpringBoot.models.ReadingGroup;
import saraymarcos.ProyectSpringBoot.services.readingGroup.ReadingGroupService;

import java.util.List;

@RestController
@RequestMapping("/connectedReads/readingGroups")
@Slf4j
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
        log.info("getAllReadingGroups");
        return ResponseEntity.ok(readingGroupMapper.toResponse(readingGroupService.findAll()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReadingGroupResponseDto> getReadingGroupById(
            @PathVariable Long id
    ){
        log.info("getReadingGroupById");
        return ResponseEntity.ok(readingGroupMapper.toResponse(readingGroupService.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ReadingGroupResponseDto>> getReadingGroupsByName(
            @PathVariable String name
    ){
        log.info("getReadingGroupsByName");
        return ResponseEntity.ok(readingGroupMapper.toResponse(readingGroupService.findReadingGroupsByName(name)));
    }

    @PostMapping("/create")
    public ResponseEntity<ReadingGroupResponseDto> postReadingGroup(
            @RequestBody ReadingGroupRequestDto readingGroupRequestDto
    ){
        log.info("postReadingGroup");
        ReadingGroup readingGroupSaved = readingGroupService.save(readingGroupMapper.toModel(readingGroupRequestDto));
        return ResponseEntity.created(null).body(readingGroupMapper.toResponse(readingGroupSaved));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ReadingGroupResponseDto> putReadingGroup(
            @PathVariable Long id,
            @RequestBody ReadingGroupRequestDto readingGroupRequestDto
    ){
        log.info("putReadingGroup");
        ReadingGroup readingGroupUpdated = readingGroupService.update(id, readingGroupMapper.toModel(readingGroupRequestDto));
        return ResponseEntity.ok(readingGroupMapper.toResponse(readingGroupUpdated));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ReadingGroupResponseDto> deleteReadingGroup(
            @PathVariable Long id
    ){
        log.info("deleteReadingGroup");
        readingGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
