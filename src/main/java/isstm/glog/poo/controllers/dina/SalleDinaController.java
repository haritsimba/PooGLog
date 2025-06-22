package isstm.glog.poo.controllers.dina;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import isstm.glog.poo.entities.dina.SalleDina;
import isstm.glog.poo.services.dina.SalleDinaService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dina/salles")
public class SalleDinaController {
    private final SalleDinaService service;

    public SalleDinaController(SalleDinaService service) {
        this.service = service;
    }

    @GetMapping
    public List<SalleDina> all() {
        return service.findAll();
    }

    @PostMapping
    public SalleDina create(@RequestBody SalleDina s) {
        return service.save(s);
    }
}
