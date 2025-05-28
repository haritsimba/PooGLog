package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;
    private String statut;


    @CreationTimestamp
    private Instant date;

    private double frais;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preinscription_id")
    private PreInscription dossiers;

}
