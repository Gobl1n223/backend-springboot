package tasklist.backendspringboot.service;

import org.springframework.stereotype.Service;

import tasklist.backendspringboot.entity.Category;
import tasklist.backendspringboot.entity.Priority;
import tasklist.backendspringboot.repository.CategoryRepository;
import tasklist.backendspringboot.repository.PriorityRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByColorAsc();
    }

    public Priority add(Priority priority){
        return priorityRepository.save(priority);
    }

    public Priority update(Priority priority){
        return priorityRepository.save(priority);
    }

    public Priority findById(Long id){
        return priorityRepository.findById(id).get();
    }

    public List<Priority> findByTitle(String text){
        return priorityRepository.findByTitle(text);
    }

    public void deleteById(Long id){
        priorityRepository.deleteById(id);
    }
}
