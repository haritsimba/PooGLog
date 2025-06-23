package isstm.glog.poo.mapper.haritsimba;

import isstm.glog.poo.dtos.haritsimba.response.TimeSlotOut;
import isstm.glog.poo.entities.haritsimba.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotOutMapper {
    public static TimeSlotOut mapToDto(TimeSlot timeSlot){
        TimeSlotOut timeSlotOut = new TimeSlotOut();
        timeSlotOut.setEndTime(timeSlot.getEndTime());
        timeSlotOut.setStartTime(timeSlot.getStartTime());
        timeSlotOut.setId(timeSlot.getId());

        timeSlotOut.setTeacherId(timeSlot.getTeacher() != null ? timeSlot.getTeacher().getTeacherId() : 0L);
        timeSlotOut.setTeacherName(timeSlot.getTeacher() != null ? timeSlot.getTeacher().getName() : "");

        timeSlotOut.setSubjectId(timeSlot.getSubject() != null ? timeSlot.getSubject().getSubjectId() : 0L);
        timeSlotOut.setSubjectName(timeSlot.getSubject() != null ? timeSlot.getSubject().getName() : "");

        timeSlotOut.setClassLevel(timeSlot.getClasse() != null ? timeSlot.getClasse().getNiveau() : "");
        timeSlotOut.setClassDepartment(timeSlot.getClasse() != null ? timeSlot.getClasse().getParcours() : "");

        timeSlotOut.setDay(timeSlot.getDay());

        return timeSlotOut;
    }


    public static List<TimeSlotOut> mapToDtoFromList(List<TimeSlot> timeSlots){
        List<TimeSlotOut> dtos = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots){
            TimeSlotOut dto = mapToDto(timeSlot);
            dtos.add(dto);
        }
        return dtos;
    }
}
