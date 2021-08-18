package tasklist.backendspringboot.controller;

import org.springframework.web.bind.annotation.*;
import tasklist.backendspringboot.entity.Category;
import tasklist.backendspringboot.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test1")
    public List<Category> test(){
        List<Category> list = categoryRepository.findAll();
        return (list);
    }
    @PostMapping("/add")
    public void add(@RequestBody Category category){
        categoryRepository.save(category);
    }
}
