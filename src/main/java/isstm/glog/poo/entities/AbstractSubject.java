package isstm.glog.poo.entities;

import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.entities.haritsimba.Teacher;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Cette class centralise les rélations pour les entités (matières).
 * @author Franck Haritsimba
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractSubject {
    @Column(name = "subject_id")
    protected Long subjectId;
    /**************************
     * Relations
     *************************/
    // Prof responsable de la matière
    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id")
    protected AbstractTeacher teacher;

    // Classe liée à la matière
    @ManyToOne(targetEntity = Classe.class)
    @JoinColumn(name = "classe_id")
    protected AbstractClasse classe;

    // Rélation entre matière et emploi du temps
    @OneToMany(mappedBy = "subject",targetEntity = TimeSlot.class)
    protected List<? extends AbstractTimeSlot> timeSlots;

    /**************************
     * Méthodes Abstraits
     *************************/

    // Récuperation du nom de la matière
    public abstract String getName();
}
