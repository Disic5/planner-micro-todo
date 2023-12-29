package ru.den.plannertodo.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.dto.PriorityDto;
import ru.den.planner.entity.Priority;
import ru.den.plannertodo.search.PrioritySearchValue;
import ru.den.plannertodo.service.impl.PriorityServiceImpl;
import ru.den.plannerutils.rest.resttemplate.UserRestBuilder;
import ru.den.plannerutils.rest.webclient.UserWebClientBuilder;

import java.util.List;

@RestController
@RequestMapping("/priority")
@RequiredArgsConstructor
public class PriorityController {
    private final UserRestBuilder userRestBuilder;
    private final UserWebClientBuilder userWebClientBuilder;

    private final PriorityServiceImpl service;

    @PostMapping("/id")
    public ResponseEntity<PriorityDto> findById(@RequestBody Long id) {
        try{
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e){
            throw new EntityNotFoundException("Priority with this id " + id + " not found");
        }
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if (userWebClientBuilder.userExists(priority.getUserId())){
            return ResponseEntity.ok(service.addPriority(priority));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        service.updatePriority(priority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityDto>> search(@RequestBody PrioritySearchValue prioritySearchValue) {
        if (prioritySearchValue.getUserId() != null && prioritySearchValue.getUserId() == 0) {
            return new ResponseEntity("missed param: userId", HttpStatus.NOT_ACCEPTABLE);
        }
        List<PriorityDto> list = service.findByTitle(prioritySearchValue.getTitle(), prioritySearchValue.getUserId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable Long id) {
        try {
            service.deletePriority(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriorityDto>> findAllPriority(){
        return ResponseEntity.ok(service.findAllPriority());
    }

}
