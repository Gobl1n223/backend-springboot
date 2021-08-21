package tasklist.backendspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tasklist.backendspringboot.entity.Stat;
import tasklist.backendspringboot.repository.StatRepository;
import tasklist.backendspringboot.service.StatService;

import java.util.List;

@RestController
public class StatController {

    private StatService statService;

    private final Long defaultId = 1l;

    public StatController(StatService statService) {
        this.statService = statService;
    }
    
    @GetMapping("/stat")
    public ResponseEntity<Stat> findById(){
        System.out.println("StatContr: findById------------------------");
        return ResponseEntity.ok(statService.findById(defaultId));
    }
}
