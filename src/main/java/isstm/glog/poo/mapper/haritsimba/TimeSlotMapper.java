package isstm.glog.poo.mapper.haritsimba;

import isstm.glog.poo.dtos.haritsimba.response.TimeSlotRepresentation;
import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.entities.haritsimba.Teacher;
import isstm.glog.poo.entities.haritsimba.TimeSlot;

public class TimeSlotMapper {
    public static TimeSlotRepresentation mapToRepresentation(TimeSlot timeSlot){
        TimeSlotRepresentation representation = new TimeSlotRepresentation();
        representation.setId(timeSlot.getId());
        representation.setDay(timeSlot.getDay().toString());
        representation.setStartTime(timeSlot.getStartTime());
        representation.setEndTime(timeSlot.getEndTime());

        Teacher teacher = (Teacher) timeSlot.getTeacher();
        Classe classe = (Classe) timeSlot.getClasse();
        Subject subject = (Subject) timeSlot.getSubject();
        if(teacher!=null){
            representation.setTeacherId(teacher.getId());
            representation.setTeacherDisplayName(teacher.getName());
        }
        if(classe!=null){
            representation.setClasseId(classe.getId());
            representation.setClasseDepartment(classe.getParcours());
            representation.setClasseLevel(classe.getNiveau());
            representation.setClasseDisplayName(classe.getNiveau()+" "+classe.getParcours());
        }
        representation.setSubjectId(subject.getId());
        representation.setSubjectDisplayName(subject.getName());

        return  representation;
    }
}
