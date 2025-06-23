package isstm.glog.poo.controllers.haritsimba;

import isstm.glog.poo.dtos.haritsimba.TeacherDto;
import isstm.glog.poo.services.haritsimba.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TeacherDto dto){
        return ResponseEntity.ok(teacherService.create(dto));
    }
}
