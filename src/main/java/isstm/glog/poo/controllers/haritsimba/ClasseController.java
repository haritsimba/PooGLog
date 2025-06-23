package isstm.glog.poo.controllers.haritsimba;

import isstm.glog.poo.dtos.haritsimba.ClasseDto;
import isstm.glog.poo.services.haritsimba.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@RequestMapping("classes")
@CrossOrigin
public class ClasseController {
    @Autowired
    ClasseService classeService;

    @GetMapping("/findall")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(classeService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> createClasse(@RequestBody ClasseDto dto){
        return ResponseEntity.ok(classeService.createClasse(dto));
    }

}
