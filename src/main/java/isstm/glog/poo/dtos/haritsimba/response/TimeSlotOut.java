package isstm.glog.poo.dtos.haritsimba.response;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class TimeSlotOut {
    Long id;
    String teacherName;
    String subjectName;
    String classLevel;
    String classDepartment;
    LocalTime startTime;
    LocalTime endTime;
    DayOfWeek day;
}
