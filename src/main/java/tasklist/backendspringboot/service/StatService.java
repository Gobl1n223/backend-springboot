package tasklist.backendspringboot.service;

import org.springframework.stereotype.Service;
import tasklist.backendspringboot.entity.Stat;
import tasklist.backendspringboot.repository.StatRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatService {
    private final StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    public Stat findById(Long id){
        return statRepository.findById(id).get();
    }

}
