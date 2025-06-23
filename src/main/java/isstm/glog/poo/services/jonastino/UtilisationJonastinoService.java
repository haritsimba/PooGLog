package isstm.glog.poo.services.jonastino;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import isstm.glog.poo.entities.jonastino.MaterielJonastino;
import isstm.glog.poo.entities.jonastino.UtilisationJonastino;
import isstm.glog.poo.repositories.jonastino.UtilisationJonastinoRepository;

@Service
@Transactional
public class UtilisationJonastinoService {
    private final UtilisationJonastinoRepository repo;
    public UtilisationJonastinoService(UtilisationJonastinoRepository repo) { this.repo = repo; }
    public List<UtilisationJonastino> findAll() { return repo.findAll(); }
    public List<UtilisationJonastino> findByMateriel(Long id) { return repo.findByMaterielId(id); }
    public List<UtilisationJonastino> findByUtilisateur(String u) { return repo.findByUtilisateurIgnoreCase(u); }
    public boolean isAvailable(Long mId, LocalDateTime d, LocalDateTime f) {
        return !repo.existsByMaterielIdAndDebutLessThanAndFinGreaterThan(mId, f, d);
    }
    public UtilisationJonastino create(Long mId, LocalDateTime d, LocalDateTime f, String u) {
        if (!isAvailable(mId, d, f)) throw new IllegalArgumentException("Conflit d'utilisation");
        UtilisationJonastino uo = new UtilisationJonastino();
        uo.setMateriel(new MaterielJonastino()); uo.getMateriel().setId(mId);
        uo.setDebut(d); uo.setFin(f); uo.setUtilisateur(u);
        return repo.save(uo);
    }
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Introuvable");
        repo.deleteById(id);
    }
}