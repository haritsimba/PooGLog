package isstm.glog.poo.dtos.elio.response;

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
    private String status; // pour l'état de l'inscription, comme "Succès" ou "Échec"

}
