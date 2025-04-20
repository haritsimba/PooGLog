package isstm.glog.poo.controllers.haritsimba;

import isstm.glog.poo.dtos.haritsimba.request.CreateTimeSlotFullCheck;
import isstm.glog.poo.dtos.haritsimba.response.OrchesterResponse;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import isstm.glog.poo.mapper.haritsimba.TimeSlotMapper;
import isstm.glog.poo.services.haritsimba.TimeSlotOrchesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "timeSlot")
public class TimeSlotController {
    @Autowired
    TimeSlotOrchesterService timeSlotOrchesterService;

    @PostMapping("")
    public ResponseEntity<?> createTimeSlot(@RequestBody CreateTimeSlotFullCheck timeSlotFullCheck){
        OrchesterResponse timeSlotOrchesterResponse = timeSlotOrchesterService.createTimeSlotCheckSubjectAndTeacher(
                timeSlotFullCheck.getDay(),
                timeSlotFullCheck.getStartTime(),
                timeSlotFullCheck.getEndTime(),
                timeSlotFullCheck.getTeacherId(),
                timeSlotFullCheck.getSubjectId(),
                timeSlotFullCheck.getClasseId()
        );

        if(timeSlotOrchesterResponse.getStatus().isSuccessFull()){
            return ResponseEntity.ok(TimeSlotMapper.mapToRepresentation((TimeSlot) timeSlotOrchesterResponse.getEntity()));
        }else {
            return switch (timeSlotOrchesterResponse.getStatus()){
                case ERROR -> ResponseEntity.status(403).body(new Exception(timeSlotOrchesterResponse.getMessage()));
                case UNAUTHORIZED -> ResponseEntity.status(401).body(new Exception(timeSlotOrchesterResponse.getMessage()));
                default -> ResponseEntity.internalServerError().body(new Exception(timeSlotOrchesterResponse.getMessage()));
            };
        }
    }
}
