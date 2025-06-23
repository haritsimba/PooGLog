package isstm.glog.poo.dtos.haritsimba.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeSlotRepresentation {
    Long Id;
    String Day;
    LocalTime startTime;
    LocalTime endTime;
    Long teacherId;
    String teacherDisplayName;
    Long subjectId;
    String subjectDisplayName;
    Long classeId;
    String classeDisplayName;
    String classeLevel;
    String classeDepartment;
}
