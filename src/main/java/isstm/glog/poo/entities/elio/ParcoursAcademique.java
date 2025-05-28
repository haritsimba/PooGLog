package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParcoursAcademique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //etude
    private String anneeBac;
    private String numeroInscription;
    private String serieBac;
    private String mentionBac;
    private String universiteDelivrance;
    private String inscriptionAnterieure;
    private String universiteAnterieure;
    private String etablissementAnterieur;
    private String parcoursAnterieur;

    //chois de formation
    private String niveau;
    private String mention;
    private String parcours;

}
