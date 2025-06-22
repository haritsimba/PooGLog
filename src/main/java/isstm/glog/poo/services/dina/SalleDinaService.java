package isstm.glog.poo.services.dina;

import org.springframework.stereotype.Service;
import java.util.List;
import isstm.glog.poo.entities.dina.SalleDina;
import isstm.glog.poo.repositories.dina.SalleDinaRepository;

@Service
public class SalleDinaService {
    private final SalleDinaRepository repo;

    public SalleDinaService(SalleDinaRepository repo) {
        this.repo = repo;
    }

    public List<SalleDina> findAll() {
        return repo.findAll();
    }

    public SalleDina save(SalleDina s) {
        return repo.save(s);
    }

    // TODO: update, delete, recherche par batiment/etage...
}
