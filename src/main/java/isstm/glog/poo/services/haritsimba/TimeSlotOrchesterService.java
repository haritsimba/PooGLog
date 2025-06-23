package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.request.TimeSlotDeleteDto;
import isstm.glog.poo.dtos.haritsimba.request.TimeSlotPatchDto;
import isstm.glog.poo.dtos.haritsimba.response.OrchesterResponse;
import isstm.glog.poo.entities.AbstractClasse;
import isstm.glog.poo.entities.AbstractSubject;
import isstm.glog.poo.entities.AbstractTeacher;
import isstm.glog.poo.entities.AbstractTimeSlot;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import isstm.glog.poo.enumerations.haritsimba.OrchesterResponseStatus;
import isstm.glog.poo.mapper.haritsimba.TimeSlotOutMapper;
import isstm.glog.poo.repositories.haritsimba.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.DTD;
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

    public OrchesterResponse findByClasseId(Long classeId){
        return new OrchesterResponse(TimeSlotOutMapper.mapToDtoFromList(timeSlotRepository.findByClasseId(classeId)),OrchesterResponseStatus.SUCCESS,"All Timeslot by classe");
    }

    @Transactional
    public OrchesterResponse deleteTimeSlot(Long classeId, TimeSlotDeleteDto dto) {
        timeSlotRepository.deleteByClasseIdAndStartTimeAndEndTimeAndDay(classeId,dto.getStartTime(),dto.getEndTime(),dto.getDay());
        return new OrchesterResponse(null,OrchesterResponseStatus.SUCCESS,"Deleted Successfully");
    }

    public OrchesterResponse createOrUpdate(Long classeId, TimeSlotPatchDto dto) {
        TimeSlot timeSlot;
        System.out.println(dto.toString());
        // UPDATE
        if (dto.getId() != null) {
            timeSlot = timeSlotRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("TimeSlot not found with ID " + dto.getId()));
        } else {
            timeSlot = new TimeSlot();
            timeSlot.setId(null);
        }

        // On met à jour les champs dans les deux cas
        timeSlot.setDay(dto.getDay());
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());

        // Sécurité : vérifier si les éléments existent, sinon lancer une exception explicite
        subjectRepository.findById(dto.getSubjectId())
                .ifPresentOrElse(timeSlot::setSubject, () -> {
                    throw new RuntimeException("Subject not found with ID " + dto.getSubjectId());
                });

        classeRepository.findById(classeId)
                .ifPresentOrElse(timeSlot::setClasse, () -> {
                    throw new RuntimeException("Classe not found with ID " + classeId);
                });
        timeSlot.setTeacher(teacherRepository.findById(timeSlot.getSubject().getTeacher().getTeacherId()).get());
        // Sauvegarde
        timeSlotRepository.save(timeSlot);

        String message = (dto.getId() != null) ? "Updated Successfully" : "Created Successfully";
        return new OrchesterResponse(TimeSlotOutMapper.mapToDto(timeSlot), OrchesterResponseStatus.SUCCESS, message);
    }

}

