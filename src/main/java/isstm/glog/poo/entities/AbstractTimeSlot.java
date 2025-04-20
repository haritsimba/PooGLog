package isstm.glog.poo.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class AbstractSchedule {

    @OneToOne
    AbstractSubject subject;
    @OneToMany
    AbstractTeacher teacher;
    @OneToMany
    AbstractClasse classe;
}
