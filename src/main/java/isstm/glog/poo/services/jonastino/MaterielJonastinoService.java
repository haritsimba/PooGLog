package isstm.glog.poo.services.jonastino;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import isstm.glog.poo.entities.jonastino.MaterielJonastino;
import isstm.glog.poo.repositories.jonastino.MaterielJonastinoRepository;

@Service
@Transactional
public class MaterielJonastinoService {
    private final MaterielJonastinoRepository repo;
    public MaterielJonastinoService(MaterielJonastinoRepository repo) { this.repo = repo; }
    public List<MaterielJonastino> findAll() { return repo.findAll(); }
    public MaterielJonastino save(MaterielJonastino m) { return repo.save(m); }
    public void delete(Long id) { repo.deleteById(id); }
}