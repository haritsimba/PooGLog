package isstm.glog.poo.controllers.sarobidy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

import isstm.glog.poo.dtos.sarobidy.request.CertificateRequest;
import isstm.glog.poo.services.sarobidy.CertificateService;
import isstm.glog.poo.services.sarobidy.CreationHistoryService;
import isstm.glog.poo.services.sarobidy.StudentRequestedService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "certificate")
public class CertificateController {


    @Autowired
    StudentRequestedService studentRequestedService;

    @Autowired
    CertificateService certificateService;

    @Autowired
    CreationHistoryService creationHistoryService;

    @PostMapping("/creation")
    public ResponseEntity<?> createNewCertificate(@RequestBody CertificateRequest request){
        
        if(studentRequestedService.findStudentByRegistrationNumber(request.getRegistrationNumber()).isEmpty()){
            return ResponseEntity.status(404).body("Etudiant introuvable");
        }

        if(creationHistoryService.verifyHistoryBeforeCreation(request.getType(), request.getRegistrationNumber()) != false){
            return ResponseEntity.status(404).body("L'étudiant a atteint la limite pour ce type de certificat");
        }

        try {
            return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=certificate.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(certificateService.createNewCertificate(request.getType(), request.getRegistrationNumber()));
        }
        catch(RuntimeException e){
            return ResponseEntity.status(500).body("Erreur interne lors du chargement du fichier pdf");
        }
    }
}
