package isstm.glog.poo.repositories.dina;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import isstm.glog.poo.entities.dina.ReservationDina;

public interface ReservationDinaRepository extends JpaRepository<ReservationDina, Long> {
    /** Retourne les réservations qui chevauchent [debut,fin[ */
    @Query("""
      SELECT r FROM ReservationDina r
       WHERE r.salle.id = :salleId
         AND r.debut < :fin
         AND r.fin > :debut
    """)
    List<ReservationDina> findConflicts(
      @Param("salleId") Long salleId,
      @Param("debut") LocalDateTime debut,
      @Param("fin")   LocalDateTime fin
    );
    /** Réservations d’une salle donnée */
List<ReservationDina> findBySalleId(Long salleId);

/** Réservations d’un organisateur (ignore la casse) */
List<ReservationDina> findByOrganisateurIgnoreCase(String organisateur);
}
