package isstm.glog.poo.entities;

import isstm.glog.poo.entities.haritsimba.Classe;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractStudent {

    @Column(name = "student_id")
    protected Long studentId;

    @ManyToOne(targetEntity = Classe.class)
    @JoinColumn(name = "classe_id")
    protected AbstractClasse classe;
    
    protected abstract String getName();

}
