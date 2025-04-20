package isstm.glog.poo.dtos.haritsimba.request;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class CreateTimeSlotFullCheck {
    DayOfWeek day;
    LocalTime startTime;
    LocalTime endTime;
    Long teacherId;
    Long subjectId;
    Long classeId;
}
