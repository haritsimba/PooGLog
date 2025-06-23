package isstm.glog.poo.repositories.haritsimba;

import isstm.glog.poo.entities.AbstractSubject;
import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
