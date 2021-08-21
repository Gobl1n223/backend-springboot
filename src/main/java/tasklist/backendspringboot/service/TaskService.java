package tasklist.backendspringboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tasklist.backendspringboot.entity.Category;
import tasklist.backendspringboot.entity.Task;
import tasklist.backendspringboot.repository.CategoryRepository;
import tasklist.backendspringboot.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task add(Task task){
        return taskRepository.save(task);
    }

    public Task update(Task task){
        return taskRepository.save(task);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).get();
    }

    public Page findByParam(String title, Integer completed, Long priorityId, Long categoryId, PageRequest pageRequest){
        return taskRepository.findByParam(title, completed, priorityId, categoryId, pageRequest);
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
}
