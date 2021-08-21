package tasklist.backendspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasklist.backendspringboot.entity.Task;
import tasklist.backendspringboot.search.TaskSearchValues;
import tasklist.backendspringboot.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {


    private TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll(){

        return ResponseEntity.ok(taskService.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task){
        System.out.println("TaskController: add------------------------");
        if(task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.add(task));
    }
    @PutMapping("/update")
    public ResponseEntity update (@RequestBody Task task){
        System.out.println("TaskController: update------------------------");
        if(task.getId() == 0 && task.getId() == null){
            return new ResponseEntity("only categories can be correct", HttpStatus.NOT_ACCEPTABLE);
        }
        if(task.getTitle().trim().length() == 0 && task.getTitle() == null){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }
        taskService.update(task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        System.out.println("TaskController: findById------------------------");
        Task task = null;
        try {
            task = taskService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return  ResponseEntity.ok(task);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteById(@PathVariable Long id){
        System.out.println("TaskController: delete------------------------");
        Task task = null;
        try{
            taskService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = "+ id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues){
        System.out.println("TaskController: search--------------");
        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() :null;
        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() :null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() :null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() :null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() :null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() :null;
        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() :null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);


       Page result =  taskService.findByParam(title, completed, priorityId, categoryId,pageRequest );
        return ResponseEntity.ok(result);
}
}
