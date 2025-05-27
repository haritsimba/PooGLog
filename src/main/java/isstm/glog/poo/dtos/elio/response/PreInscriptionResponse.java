package isstm.glog.poo.dtos.elio.response;

import isstm.glog.poo.entities.elio.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PreInscriptionResponse {
    // Getters and Setters
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private Status status; // pour l'état de l'inscription, comme "Succès" ou "Échec"

}
