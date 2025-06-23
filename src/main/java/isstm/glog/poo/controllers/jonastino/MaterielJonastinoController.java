package isstm.glog.poo.controllers.jonastino;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import isstm.glog.poo.entities.jonastino.MaterielJonastino;
import isstm.glog.poo.services.jonastino.MaterielJonastinoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/jonastino/materiels")
public class MaterielJonastinoController {
    private final MaterielJonastinoService service;
    public MaterielJonastinoController(MaterielJonastinoService service) { this.service = service; }
    @GetMapping public List<MaterielJonastino> all() { return service.findAll(); }
    @PostMapping public MaterielJonastino create(@RequestBody MaterielJonastino m) { return service.save(m); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}