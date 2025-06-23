package isstm.glog.poo.controllers.jonastino;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;
import isstm.glog.poo.entities.jonastino.UtilisationJonastino;
import isstm.glog.poo.services.jonastino.UtilisationJonastinoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/jonastino/utilisations")
public class UtilisationJonastinoController {
    private final UtilisationJonastinoService service;
    public UtilisationJonastinoController(UtilisationJonastinoService service) { this.service = service; }
    @GetMapping public List<UtilisationJonastino> list(
        @RequestParam(required=false) Long materielId,
        @RequestParam(required=false) String utilisateur
    ) {
        if (materielId != null) return service.findByMateriel(materielId);
        if (utilisateur != null) return service.findByUtilisateur(utilisateur);
        return service.findAll();
    }
    @GetMapping("/availability") public boolean avail(
        @RequestParam Long materielId, @RequestParam String debut, @RequestParam String fin
    ) {
        return service.isAvailable(materielId, LocalDateTime.parse(debut), LocalDateTime.parse(fin));
    }
    @PostMapping public UtilisationJonastino create(
        @RequestParam Long materielId, @RequestParam String debut,
        @RequestParam String fin, @RequestParam String utilisateur
    ) {
        return service.create(materielId, LocalDateTime.parse(debut), LocalDateTime.parse(fin), utilisateur);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}
