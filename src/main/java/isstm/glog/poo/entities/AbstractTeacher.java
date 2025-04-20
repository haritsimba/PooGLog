package isstm.glog.poo.entities;

import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@MappedSuperclass
@Getter
public abstract class AbstractTeacher {
    @Column(name = "teacher_id")
    protected Long teacherId;

    // Rélation entre prof et matières
    @OneToMany(mappedBy = "teacher",targetEntity = Subject.class)
    protected List<? extends AbstractSubject> subjects;

    // Rélation entre prof et emploi du temps
    @OneToMany(mappedBy = "teacher",targetEntity = TimeSlot.class)
    protected List<? extends AbstractTimeSlot> timeSlots;

    // Récuperation du nom du prof
    public abstract String getName();
}
