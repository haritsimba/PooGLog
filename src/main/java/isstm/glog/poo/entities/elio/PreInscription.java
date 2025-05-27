package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private Status status = Status.EN_ATTENTE;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


    public PreInscription() {
        this.documents = new Documents();  // Initialisation pour éviter les NPE
    }

}
