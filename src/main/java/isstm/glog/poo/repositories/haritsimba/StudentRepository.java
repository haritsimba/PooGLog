package isstm.glog.poo.repositories.haritsimba;

import isstm.glog.poo.entities.haritsimba.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
