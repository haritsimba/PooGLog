package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.entities.AbstractClasse;
import isstm.glog.poo.entities.AbstractSubject;
import isstm.glog.poo.entities.AbstractTeacher;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import isstm.glog.poo.repositories.haritsimba.TimeSlotRepository;
import isstm.glog.poo.services.TimeSlotServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TimeSlotService implements TimeSlotServiceInterface {
    @Autowired
    TimeSlotRepository timeSlotRepository;

    public TimeSlot createTimeSlot(DayOfWeek day, LocalTime start, LocalTime end, AbstractSubject subject, AbstractClasse classe, AbstractTeacher teacher){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(start);
        timeSlot.setEndTime(end);
        timeSlot.setDay(day);
        timeSlot.setTeacher(teacher);
        timeSlot.setSubject(subject);
        timeSlot.setClasse(classe);

        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot updateTimeSlotDayAndTime(Long timeSlotId, DayOfWeek day, LocalTime start, LocalTime end){
        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(timeSlotId);
        if(optionalTimeSlot.isEmpty()){
            return null;
        }
        TimeSlot timeSlot = optionalTimeSlot.get();
        timeSlot.setDay(day);
        timeSlot.setStartTime(start);
        timeSlot.setEndTime(end);
        return timeSlotRepository.save(timeSlot);
    }
}
