package tasklist.backendspringboot.service;

import org.springframework.stereotype.Service;

import tasklist.backendspringboot.entity.Category;
import tasklist.backendspringboot.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    public Category add(Category category){
    return categoryRepository.save(category);
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public List<Category> findByTitle(String text){
        return categoryRepository.findByTitle(text);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
