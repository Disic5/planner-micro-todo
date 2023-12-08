package ru.den.plannertodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.entity.Task;
import ru.den.plannertodo.search.TaskSearchValues;
import ru.den.plannertodo.service.TaskService;

import java.util.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;
    private static final String ID_COLUMN = "id";

    @PostMapping("/id")
    public ResponseEntity<Task> findById(@RequestBody Long id){
        Optional<Task> task = service.findTaskById(id);
        if (task.isPresent()){
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            throw new NoSuchElementException("Task with this id " + id + " not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAllTask(){
        return ResponseEntity.ok(service.findAllTask());
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.ok(service.addTask(task));
    }

    @PutMapping(value = "/update", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        service.updateTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePriority(@PathVariable Long id) {
        if(service.findTaskById(id).isPresent()){
            service.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search (@RequestBody TaskSearchValues taskSearchValues){
        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Long userId = taskSearchValues.getUserId() != null ? taskSearchValues.getUserId() : null;
        Boolean completed = taskSearchValues.getCompleted() != null && taskSearchValues.getCompleted() == 1;
        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;
        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;
        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;

        if (userId == null || userId == 0){
            return new ResponseEntity("missed param: userId", HttpStatus.NOT_ACCEPTABLE);
        }
        Date dateFrom = null;
        Date dateTo = null;

        //выставить 00:00 для начальной даты(если она указана)
        if (taskSearchValues.getDateFrom() != null){
            Calendar calendarFrom = Calendar.getInstance();
            calendarFrom.setTime(taskSearchValues.getDateFrom());
            calendarFrom.set(Calendar.HOUR_OF_DAY, 0);
            calendarFrom.set(Calendar.MINUTE, 0);
            calendarFrom.set(Calendar.SECOND, 0);
            calendarFrom.set(Calendar.MILLISECOND, 1);
            dateFrom = calendarFrom.getTime(); // записываем начальную дату с 00:00
        }
        //выставить 23:59 для конечной даты(если она указана)
        if (taskSearchValues.getDateTo() != null){
            Calendar calendarTo = Calendar.getInstance();
            calendarTo.setTime(taskSearchValues.getDateTo());
            calendarTo.set(Calendar.HOUR_OF_DAY, 23);
            calendarTo.set(Calendar.MINUTE, 59);
            calendarTo.set(Calendar.SECOND, 59);
            calendarTo.set(Calendar.MILLISECOND, 999);
            dateTo = calendarTo.getTime(); // записываем конечную дату с 23:59
        }

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length()==0 || sortDirection.trim().equals("asc")?
                Sort.Direction.ASC : Sort.Direction.DESC;
        
        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);

        Page<Task> result = service.findByParams(title,completed,priorityId,categoryId,userId,dateFrom,dateTo,pageRequest);

        return ResponseEntity.ok(result);
    }

}
