package tasklist.backendspringboot.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tasklist.backendspringboot.entity.Priority;
import tasklist.backendspringboot.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT c FROM Task c where " +
            "(:title is null or :title='' or lower (c.title) like lower(concat('%', :title, '%'))) and" +
            "(:completed is null or c.completed=:completed) and"+
            "(:priorityId is null or c.priority.id=:priorityId) and "+
            "(:categoryId is null or c.categoryByCategoryId.id=:categoryId)")
    Page<Task> findByParam(@Param("title") String title,
                           @Param("completed") Integer completed,
                           @Param("priorityId")  Long priorityId,
                           @Param("categoryId") Long categoryId,
                           Pageable pageable);
}
