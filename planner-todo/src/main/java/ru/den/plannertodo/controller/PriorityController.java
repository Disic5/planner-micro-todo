package ru.den.plannertodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.entity.Priority;
import ru.den.plannertodo.search.PrioritySearchValue;
import ru.den.plannertodo.service.PriorityService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityService service;

    @PostMapping("/id")
    public ResponseEntity<Priority> findById(@RequestBody Long id) {
        Optional<Priority> priority = service.findById(id);
        if (priority.isPresent()) {
            return new ResponseEntity<>(priority.get(), HttpStatus.OK);
        } else {
            throw new NoSuchElementException("Priority with this id " + id + " not found");
        }
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        return ResponseEntity.ok(service.addPriority(priority));
    }

    @PutMapping(value = "/update", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        service.updatePriority(priority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValue prioritySearchValue) {
        if (prioritySearchValue.getUserId() != null && prioritySearchValue.getUserId() == 0) {
            return new ResponseEntity("missed param: userId", HttpStatus.NOT_ACCEPTABLE);
        }
        List<Priority> list = service.findByTitle(prioritySearchValue.getTitle(), prioritySearchValue.getUserId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePriority(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deletePriority(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Priority>> findAllPriority(){
        return ResponseEntity.ok(service.findAllPriority());
    }

}
