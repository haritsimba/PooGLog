package isstm.glog.poo.services.dina;

import org.springframework.stereotype.Service;
import java.util.List;
import isstm.glog.poo.entities.dina.StudentDina;
import isstm.glog.poo.repositories.dina.StudentDinaRepository;

@Service
public class StudentDinaService {
    private final StudentDinaRepository repo;

    public StudentDinaService(StudentDinaRepository repo) {
        this.repo = repo;
    }

    public List<StudentDina> findAll() {
        return repo.findAll();
    }

    public StudentDina save(StudentDina s) {
        return repo.save(s);
    }

    // add update, delete, search by programme, etc.
}
