package isstm.glog.poo.controllers.haritsimba;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import isstm.glog.poo.dtos.haritsimba.request.CreateTimeSlotFullCheck;
import isstm.glog.poo.dtos.haritsimba.request.TimeSlotDeleteDto;
import isstm.glog.poo.dtos.haritsimba.request.TimeSlotPatchDto;
import isstm.glog.poo.dtos.haritsimba.response.OrchesterResponse;
import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import isstm.glog.poo.mapper.haritsimba.TimeSlotMapper;
import isstm.glog.poo.repositories.haritsimba.ClasseRepository;
import isstm.glog.poo.services.haritsimba.PdfGeneratorService;
import isstm.glog.poo.services.haritsimba.TemplateGeneratorService;
import isstm.glog.poo.services.haritsimba.TimeSlotOrchesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "timeSlot")
@Tag(name = "Emplois du temps", description = "Operations liées aux emplois du temps")
@CrossOrigin
public class TimeSlotController {
    @Autowired
    TimeSlotOrchesterService timeSlotOrchesterService;
    @Autowired
    TemplateGeneratorService templateGeneratorService;

    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    PdfGeneratorService pdfGeneratorService;

    @PostMapping("")
    @Operation(
            summary = "Ajouter une emploi de temps",
            description = "Ajout d'emploi du temps"
    )
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

    @GetMapping(value = "/pdf/{classeId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long classeId) {
        try {

            Classe classe = classeRepository.findById(classeId).get();

            Map<String, Object> data = templateGeneratorService.getTimeSlotTemplateByClasseId(classeId);
            if(data == null){
                return ResponseEntity.status(404).build();
            }
            byte[] pdf = pdfGeneratorService.generatePdfFromTemplate("timeslot-template",data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=emploi_du_temps_" + classe.getLevel() + "_" + classe.getDepartment() + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (IOException e) {
            System.out.println("Erreur lors de la génération du PDF");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "classe/{classeId}")
    public ResponseEntity<?> getTimetableByClasse(@PathVariable(value = "classeId")Long classeId){
        return ResponseEntity.ok(timeSlotOrchesterService.findByClasseId(classeId).getEntity());
    }

    @PostMapping(path = "delete/{classeId}")
    public ResponseEntity<?> deleteTimeSlot(@PathVariable(value = "classeId")Long classeId,@RequestBody TimeSlotDeleteDto dto){
        System.out.println(dto.toString());
        return ResponseEntity.ok(timeSlotOrchesterService.deleteTimeSlot(classeId,dto).getMessage());
    }

   @PatchMapping(path = "createOrUpdate/{classeId}")
    public ResponseEntity<?> createOrUpdate(@PathVariable(value = "classeId")Long classeId,@RequestBody TimeSlotPatchDto dto){
        return ResponseEntity.ok(timeSlotOrchesterService.createOrUpdate(classeId,dto).getEntity());
   }

}


