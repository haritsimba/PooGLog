package isstm.glog.poo.controllers.haritsimba;

import isstm.glog.poo.dtos.haritsimba.request.SubjectCreateDto;
import isstm.glog.poo.services.haritsimba.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subjects")
@CrossOrigin
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("classe/{classeId}")
    public ResponseEntity<?> getByClasseId(@PathVariable(value = "classeId")Long classeId){
        return ResponseEntity.ok(subjectService.findByClasseId(classeId));
    }

    @PostMapping("")
    public ResponseEntity<?> createSubject(SubjectCreateDto dto){
        return ResponseEntity.ok(subjectService.createSubject(dto));
    }
}
