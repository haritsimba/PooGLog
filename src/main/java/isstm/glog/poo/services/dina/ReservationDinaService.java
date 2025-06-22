package isstm.glog.poo.services.dina;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import isstm.glog.poo.entities.dina.ReservationDina;
import isstm.glog.poo.entities.dina.SalleDina;
import isstm.glog.poo.repositories.dina.ReservationDinaRepository;
import isstm.glog.poo.repositories.dina.SalleDinaRepository;

@Service
public class ReservationDinaService {

  private final ReservationDinaRepository repo;
  private final SalleDinaRepository salleRepo;

  public ReservationDinaService(ReservationDinaRepository repo,
      SalleDinaRepository salleRepo) {
    this.repo = repo;
    this.salleRepo = salleRepo;
  }

  public List<ReservationDina> findAll() {
    return repo.findAll();
  }

  /** Réservations par salle */
  public List<ReservationDina> findBySalle(Long salleId) {
    return repo.findBySalleId(salleId);
  }

  /** Réservations par organisateur */
  public List<ReservationDina> findByOrganisateur(String organisateur) {
    return repo.findByOrganisateurIgnoreCase(organisateur);
  }

  /** Suppression d’une réservation */
  @Transactional
  public void delete(Long reservationId) {
    if (!repo.existsById(reservationId)) {
      throw new IllegalArgumentException("Réservation introuvable : " + reservationId);
    }
    repo.deleteById(reservationId);
  }

  public boolean isAvailable(Long salleId,
      LocalDateTime debut,
      LocalDateTime fin) {
    return repo.findConflicts(salleId, debut, fin).isEmpty();
  }

  @Transactional
  public ReservationDina create(Long salleId,
      LocalDateTime debut,
      LocalDateTime fin,
      String organisateur) {
    if (!isAvailable(salleId, debut, fin)) {
      throw new IllegalArgumentException(
          "Conflit de réservation pour la salle " + salleId);
    }
    SalleDina salle = salleRepo.findById(salleId)
        .orElseThrow(() -> new IllegalArgumentException(
            "Salle introuvable : " + salleId));
    ReservationDina r = ReservationDina.builder()
        .salle(salle)
        .debut(debut)
        .fin(fin)
        .organisateur(organisateur)
        .build();
    return repo.save(r);
  }
}
