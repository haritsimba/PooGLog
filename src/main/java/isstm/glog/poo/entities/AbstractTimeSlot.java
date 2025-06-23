package isstm.glog.poo.entities;

import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.entities.haritsimba.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTimeSlot {

    @Column(name = "time_slot_id")
    protected Long timeSlotId;

    @ManyToOne(targetEntity = Subject.class)
    @JoinColumn(name = "subject_id")
    protected AbstractSubject subject;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id")
    protected AbstractTeacher teacher;

    @ManyToOne(targetEntity = Classe.class)
    @JoinColumn(name = "classe_id")
    protected AbstractClasse classe;
}
