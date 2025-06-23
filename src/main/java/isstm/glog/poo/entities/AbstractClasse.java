package isstm.glog.poo.entities;

import isstm.glog.poo.entities.haritsimba.Student;
import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractClasse {


    @Column(name = "classe_id")
    protected Long classeId;

    @OneToMany(mappedBy = "classe",targetEntity = Subject.class)
    protected List<? extends AbstractSubject> subjects;

    @OneToMany(mappedBy = "classe",targetEntity = TimeSlot.class)
    protected List<? extends AbstractTimeSlot> timeSlots;

    @OneToMany(mappedBy = "classe",targetEntity = Student.class)
    protected List<? extends AbstractStudent> students;

    public abstract String getNiveau();
    public abstract String getParcours();

}
