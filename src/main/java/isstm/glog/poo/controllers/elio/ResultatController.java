package isstm.glog.poo.controllers.elio;

import isstm.glog.poo.entities.elio.PreInscription;
import isstm.glog.poo.repositories.elio.PreInscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultats")
@CrossOrigin(origins = "*")
public class ResultatController {

    @Autowired
    private PreInscriptionRepository preInscriptionRepo;
    @GetMapping("/preinscription/{cin}")
    public ResponseEntity<?> getPreinscription(
            @PathVariable String cin,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String mention,
            @RequestParam(required = false) String parcours) {

        Optional<PreInscription> opt = preInscriptionRepo.findByCin(cin);
        if (opt.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        PreInscription res = opt.get();
        System.out.println(res.toString());
        boolean nomOK = nom == null || nom.isBlank()
                || nom.equalsIgnoreCase(res.getInformationsPersonnelles().getNom());
        boolean prenomOK   = prenom  == null || prenom.trim().isBlank()
                || prenom.equalsIgnoreCase(res.getInformationsPersonnelles().getPrenom());

        boolean mentionOK  = mention == null || mention.isBlank()
                || mention.equalsIgnoreCase(res.getParcoursAcademique().getMention());
        boolean parcoursOK = parcours == null || parcours.isBlank()
                || parcours.equalsIgnoreCase(res.getParcoursAcademique().getParcours());
        System.out.println("🔍 [DEBUG] CIN reçu : " + cin);
        System.out.println("🔍 [DEBUG] Filtres reçus - nom: " + nom + ", prénom: " + prenom + ", mention: " + mention + ", parcours: " + parcours);

        if (nomOK && prenomOK && mentionOK && parcoursOK) {
            return ResponseEntity.ok(res);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }



}
