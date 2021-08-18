package tasklist.backendspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasklist.backendspringboot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
