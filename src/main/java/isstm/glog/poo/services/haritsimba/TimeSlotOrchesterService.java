package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.response.OrchesterResponse;
import isstm.glog.poo.entities.AbstractClasse;
import isstm.glog.poo.entities.AbstractSubject;
import isstm.glog.poo.entities.AbstractTeacher;
import isstm.glog.poo.entities.AbstractTimeSlot;
import isstm.glog.poo.enumerations.haritsimba.OrchesterResponseStatus;
import isstm.glog.poo.repositories.haritsimba.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TimeSlotOrchesterService {
    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    TimeSlotRepository timeSlotRepository;
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public OrchesterResponse createTimeSlotCheckSubjectAndTeacher(
            DayOfWeek day,
            LocalTime start,
            LocalTime end,
            Long teacherId,
            Long subjectId,
            Long classeId) {
        try {
            Optional<? extends AbstractTeacher> optionalTeacher = teacherRepository.findById(teacherId);
            if (optionalTeacher.isEmpty()) {
                return new OrchesterResponse(null, OrchesterResponseStatus.ERROR, "Teacher not found");
            }

            Optional<? extends AbstractSubject> optionalSubject = subjectRepository.findById(subjectId);
            if (optionalSubject.isEmpty()) {
                return new OrchesterResponse(null, OrchesterResponseStatus.ERROR, "Subject not found");
            }

            Optional<? extends AbstractClasse> optionalClasse = classeRepository.findById(classeId);
            if (optionalClasse.isEmpty()) {
                return new OrchesterResponse(null, OrchesterResponseStatus.ERROR, "Classe not found");
            }

            AbstractTimeSlot saved = timeSlotService.createTimeSlot(day, start, end, optionalSubject.get(), optionalClasse.get(), optionalTeacher.get());

            return new OrchesterResponse(saved, OrchesterResponseStatus.SUCCESS, "Time-slot created successfully");
        } catch (Exception e) {
            return new OrchesterResponse(null, OrchesterResponseStatus.FAILED, "Cannot create time-slot");
        }
    }

    public OrchesterResponse changeTimeSlotDayAndTime(Long timeSlotId, DayOfWeek day, LocalTime start, LocalTime end) {
        Optional<? extends AbstractTimeSlot> optionalTimeSlot = timeSlotRepository.findById(timeSlotId);
        if(optionalTimeSlot.isEmpty()){
            return new OrchesterResponse(null, OrchesterResponseStatus.ERROR,"Could not find Timeslot");
        }
        AbstractTimeSlot updated = timeSlotService.updateTimeSlotDayAndTime(timeSlotId,day,start,end);
        return new OrchesterResponse(updated, OrchesterResponseStatus.SUCCESS,"Time-slot updated successfully");
    }
}

