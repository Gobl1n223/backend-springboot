package tasklist.backendspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasklist.backendspringboot.entity.Stat;

import javax.transaction.Transactional;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
