package isstm.glog.poo.dtos.haritsimba.request;

import lombok.Data;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@ToString
public class TimeSlotPatchDto {
    Long id;
    DayOfWeek day;
    LocalTime endTime;
    LocalTime startTime;
    Long subjectId;
}
