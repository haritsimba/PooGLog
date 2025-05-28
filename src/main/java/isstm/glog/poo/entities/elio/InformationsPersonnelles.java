package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class InformationsPersonnelles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //information perso
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String telephoneAlt;

    //Etat civil
    private LocalDate dateNaissance;
    private  String lieuNaissance;
    private String cin;
    private String pays;
    private String nationalite;
    private String genre;
    private String situationMatrimoniale;
    private String salarie;

}
