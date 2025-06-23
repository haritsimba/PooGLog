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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService implements TimeSlotServiceInterface {
    @Autowired
    TimeSlotRepository timeSlotRepository;

    public TimeSlot createTimeSlot(DayOfWeek day, LocalTime start, LocalTime end, AbstractSubject subject, AbstractClasse classe, AbstractTeacher teacher) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartTime(start);
        timeSlot.setEndTime(end);
        timeSlot.setDay(day);
        timeSlot.setTeacher(teacher);
        timeSlot.setSubject(subject);
        timeSlot.setClasse(classe);

        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot updateTimeSlotDayAndTime(Long timeSlotId, DayOfWeek day, LocalTime start, LocalTime end) {
        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(timeSlotId);
        if (optionalTimeSlot.isEmpty()) {
            return null;
        }
        TimeSlot timeSlot = optionalTimeSlot.get();
        timeSlot.setDay(day);
        timeSlot.setStartTime(start);
        timeSlot.setEndTime(end);
        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> addManyTimeSlots(List<TimeSlot> timeSlots) {
        List<TimeSlot> validTimeSlots = new ArrayList<>();

        for (TimeSlot slot : timeSlots) {
            List<TimeSlot> conflicts = timeSlotRepository.checkConfusionForCreate(
                    slot.getStartTime(), slot.getEndTime(), slot.getDay());

            if (conflicts.isEmpty()) {
                validTimeSlots.add(slot);
            } else {
                System.out.println("Conflit détecté pour le créneau : " + slot);
            }
        }

        return timeSlotRepository.saveAll(validTimeSlots);
    }


    public List<TimeSlot> updateManyTimeSlots(List<TimeSlot> updatedTimeSlots) {
        List<TimeSlot> savedTimeSlots = new ArrayList<>();
        for (TimeSlot updated : updatedTimeSlots) {
            Optional<TimeSlot> optional = timeSlotRepository.findById(updated.getId());
            if (optional.isPresent()) {
                TimeSlot existing = optional.get();
                existing.setDay(updated.getDay());
                existing.setStartTime(updated.getStartTime());
                existing.setEndTime(updated.getEndTime());
                existing.setSubject(updated.getSubject());
                existing.setClasse(updated.getClasse());
                existing.setTeacher(updated.getTeacher());
                savedTimeSlots.add(existing);
            }
        }
        return timeSlotRepository.saveAll(savedTimeSlots);
    }

    public List<TimeSlot> updateManyComboAdd(List<TimeSlot> timeSlots) {
        List<TimeSlot> results = new ArrayList<>();

        for (TimeSlot slot : timeSlots) {
            List<TimeSlot> conflicts;

            if (slot.getId() != null) {
                conflicts = timeSlotRepository.checkConfusionForUpdate(
                        slot.getStartTime(), slot.getEndTime(), slot.getDay(), slot.getId());
            } else {
                conflicts = timeSlotRepository.checkConfusionForCreate(
                        slot.getStartTime(), slot.getEndTime(), slot.getDay());
            }

            if (conflicts.isEmpty()) {
                if (slot.getId() != null) {
                    Optional<TimeSlot> optional = timeSlotRepository.findById(slot.getId());
                    if (optional.isPresent()) {
                        TimeSlot existing = optional.get();
                        existing.setDay(slot.getDay());
                        existing.setStartTime(slot.getStartTime());
                        existing.setEndTime(slot.getEndTime());
                        existing.setSubject(slot.getSubject());
                        existing.setClasse(slot.getClasse());
                        existing.setTeacher(slot.getTeacher());
                        results.add(existing);
                    }
                } else {
                    results.add(slot);
                }
            } else {
                System.out.println("Conflit détecté pour " + slot);
            }
        }

        return timeSlotRepository.saveAll(results);
    }



}
