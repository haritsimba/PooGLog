package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class PreInscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean acceptationReglement;
    private String lieuSignature;
    private LocalDate dateSignature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_perso_id")
    private InformationsPersonnelles informationsPersonnelles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parents_id")
    private Parents parents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parcours_id")
    private ParcoursAcademique parcoursAcademique;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documents_id")
    private Documents documents;

    public PreInscription() {
        this.documents = new Documents();  // Initialisation pour éviter les NPE
    }

}
