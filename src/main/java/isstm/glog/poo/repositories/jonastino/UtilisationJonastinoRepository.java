package isstm.glog.poo.repositories.jonastino;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import isstm.glog.poo.entities.jonastino.UtilisationJonastino;

public interface UtilisationJonastinoRepository extends JpaRepository<UtilisationJonastino, Long> {
    List<UtilisationJonastino> findByMaterielId(Long materielId);
    List<UtilisationJonastino> findByUtilisateurIgnoreCase(String utilisateur);
    boolean existsByMaterielIdAndDebutLessThanAndFinGreaterThan(
        Long materielId, LocalDateTime fin, LocalDateTime debut);
}