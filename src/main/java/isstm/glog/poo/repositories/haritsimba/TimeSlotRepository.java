package isstm.glog.poo.repositories.haritsimba;

import isstm.glog.poo.entities.haritsimba.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
    @Query("select ts from TimeSlot ts where ts.teacher.id = ?1")
    public List<TimeSlot> findByTeacherId(Long teacherId);
    @Query("select ts from TimeSlot ts where ts.classe.id = ?1")
    public List<TimeSlot> findByClasseId(Long classeId);

}