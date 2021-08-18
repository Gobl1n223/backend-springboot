package tasklist.backendspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tasklist.backendspringboot.entity.Stat;
import tasklist.backendspringboot.repository.StatRepository;

import java.util.List;

@RestController
@RequestMapping("/stat")
public class StatController {

    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    @GetMapping("/test2")
    public List<Stat> test2(){
        List<Stat> list = statRepository.findAll();
        return list;
    }
}
