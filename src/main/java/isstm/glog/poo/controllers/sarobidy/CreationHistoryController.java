package isstm.glog.poo.controllers.sarobidy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isstm.glog.poo.dtos.sarobidy.request.CertificateRequest;
import isstm.glog.poo.entities.sarobidy.CreationHistory;
import isstm.glog.poo.services.sarobidy.CreationHistoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/certificate/history")
public class CreationHistoryController {


    @Autowired
    CreationHistoryService creationHistoryService;

    @PostMapping("/new")
    public ResponseEntity<?> addNewHistory(@RequestBody CertificateRequest req){

        creationHistoryService.addNewHistory(req);

        return ResponseEntity.ok().body("Historique ajoutée avec succès!");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCertificateHistory() {
        try {
            List<CreationHistory> history = creationHistoryService.getAllHistories();

            if (history.isEmpty()) {
                return ResponseEntity.status(404).body("Aucun historique disponible.");
            }

            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne : " + e.getMessage());
        }
    }


}
