package isstm.glog.poo.dtos.haritsimba.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class UpdateTimeSlotDateTimes {
    Long TimeSlotId;
    DayOfWeek day;
    LocalTime startTime;
    LocalTime endTime;
}
