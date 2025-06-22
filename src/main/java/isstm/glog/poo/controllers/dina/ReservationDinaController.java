package isstm.glog.poo.controllers.dina;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;
import isstm.glog.poo.entities.dina.ReservationDina;
import isstm.glog.poo.services.dina.ReservationDinaService;

@RestController
@RequestMapping("/api/dina/reservations")
public class ReservationDinaController {

    private final ReservationDinaService service;

    public ReservationDinaController(ReservationDinaService service) {
        this.service = service;
    }

    /**
     * Liste les réservations.
     * - Sans paramètre : toutes les réservations.
     * - Avec salleId : réservations de la salle.
     * - Avec organisateur : réservations de l’organisateur.
     */
    @GetMapping
    public List<ReservationDina> listBy(
        @RequestParam(required = false) Long salleId,
        @RequestParam(required = false) String organisateur
    ) {
        if (salleId != null) {
            return service.findBySalle(salleId);
        }
        if (organisateur != null && !organisateur.isBlank()) {
            return service.findByOrganisateur(organisateur);
        }
        return service.findAll();
    }

    /** Vérifie la dispo d’une salle sur un créneau */
    @GetMapping("/availability")
    public boolean availability(
        @RequestParam Long salleId,
        @RequestParam("debut") String debutIso,
        @RequestParam("fin")   String finIso
    ) {
        LocalDateTime debut = LocalDateTime.parse(debutIso);
        LocalDateTime fin   = LocalDateTime.parse(finIso);
        return service.isAvailable(salleId, debut, fin);
    }

    /** Crée une réservation si le créneau est libre */
    @PostMapping
    public ReservationDina create(
        @RequestParam Long salleId,
        @RequestParam("debut") String debutIso,
        @RequestParam("fin")   String finIso,
        @RequestParam String organisateur
    ) {
        LocalDateTime debut = LocalDateTime.parse(debutIso);
        LocalDateTime fin   = LocalDateTime.parse(finIso);
        return service.create(salleId, debut, fin, organisateur);
    }

    /** Supprime une réservation par son ID */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
