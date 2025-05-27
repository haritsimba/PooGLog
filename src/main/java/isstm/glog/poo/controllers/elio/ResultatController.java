package isstm.glog.poo.controllers.elio;

import isstm.glog.poo.entities.elio.PreInscription;
import isstm.glog.poo.repositories.elio.PreInscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultats")
@CrossOrigin(origins = "*")
public class ResultatController {

    @Autowired
    private PreInscriptionRepository preInscriptionRepo;
    @GetMapping("/preinscription/{cin}")
    public ResponseEntity<?> getPreinscription(@PathVariable("cin") String cin) {
        System.out.println(cin);
        Optional<PreInscription> preInscriptionOptional = preInscriptionRepo.findByCin(cin);
        if (preInscriptionOptional.isPresent()){
            return ResponseEntity.ok(preInscriptionOptional.get());
        }
        return ResponseEntity.ok(new ArrayList<>());
    }


}
