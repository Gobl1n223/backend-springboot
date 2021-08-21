package tasklist.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasklist.backendspringboot.entity.Category;
import tasklist.backendspringboot.entity.Priority;
import tasklist.backendspringboot.repository.CategoryRepository;
import tasklist.backendspringboot.search.CategorySearchValues;
import tasklist.backendspringboot.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll(){

        System.out.println("CategoryController: all------------------------");
        return categoryService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        System.out.println("CategoryController: add------------------------");
        if(category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }
    @PutMapping("/update")
    public ResponseEntity update (@RequestBody Category category){
        System.out.println("CategoryController: update------------------------");
        if(category.getId() == 0 && category.getId() == null){
            return new ResponseEntity("only categories can be correct", HttpStatus.NOT_ACCEPTABLE);
        }
        if(category.getTitle().trim().length() == 0 && category.getTitle() == null){
            return new ResponseEntity("missed param title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        System.out.println("CategoryController: findById------------------------");
        Category category = null;
        try{
            category = categoryService.findById(id);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id){
        System.out.println("CategoryController: delete------------------------");
        Category category = null;
        try{
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = "+ id + " " + "not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    //поиск по любым параметрам
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){
        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getText()));
    }
}
