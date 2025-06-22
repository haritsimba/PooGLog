package isstm.glog.poo.controllers.dina;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import isstm.glog.poo.entities.dina.StudentDina;
import isstm.glog.poo.services.dina.StudentDinaService;

@RestController
@RequestMapping("/api/q10/dina/students")
public class StudentDinaController {
    private final StudentDinaService service;

    public StudentDinaController(StudentDinaService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentDina> all() {
        return service.findAll();
    }

    @PostMapping
    public StudentDina create(@RequestBody StudentDina s) {
        return service.save(s);
    }
}
