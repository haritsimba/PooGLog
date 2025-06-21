package isstm.glog.poo.repositories.sarobidy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import isstm.glog.poo.entities.sarobidy.CreationHistory;

public interface CreationHistoryRepository extends JpaRepository<CreationHistory, Long>{

    long countByRgNumberStudentAndTypeOfCertificate(@Param("rgNumberStudent") String rgNumberStudent, @Param("typeOfCertificate") String typeOfCertificate);
}
