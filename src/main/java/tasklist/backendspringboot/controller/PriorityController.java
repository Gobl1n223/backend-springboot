package tasklist.backendspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasklist.backendspringboot.entity.Priority;
import tasklist.backendspringboot.search.PrioritySearchValue;
import tasklist.backendspringboot.service.PriorityService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {


    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<Priority> findAll(){
        System.out.println("PriorityController: all------------------------");
        return priorityService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){
        System.out.println("PriorityController: add------------------------");
        if(priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0){
                return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }
        if(priority.getColor().trim().length() == 0 && priority.getColor() == null){
            return new ResponseEntity("missed param color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }
    @PutMapping("/update")
    public ResponseEntity update (@RequestBody Priority priority){
        System.out.println("PriorityController: update------------------------");
        if(priority.getId() == 0 && priority.getId() == null){
            return new ResponseEntity("only categories can be correct", HttpStatus.NOT_ACCEPTABLE);
        }
        if(priority.getTitle().trim().length() == 0 && priority.getTitle() == null){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }
        if(priority.getColor().trim().length() == 0 && priority.getColor() == null){
            return new ResponseEntity("missed param color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityService.update(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id){
        System.out.println("PriorityController: findById------------------------");
        Priority priority = null;
        try {
            priority = priorityService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return  ResponseEntity.ok(priority);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id){
        System.out.println("PriorityController: delete------------------------");
        Priority priority = null;
        try{
            priorityService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = "+ id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValue prioritySearchValue){
        return ResponseEntity.ok(priorityService.findByTitle(prioritySearchValue.getText()));
    }
 }
