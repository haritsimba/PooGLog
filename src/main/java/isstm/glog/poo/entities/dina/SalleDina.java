package isstm.glog.poo.entities.dina;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "SalleDina")
@Table(name = "salles_q10_dina")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalleDina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private int capacite;

    @Column(nullable = false)
    private String batiment;

    @Column(nullable = false)
    private String etage;

    @ElementCollection
    @CollectionTable(
      name = "salle_equipements_q10_dina",
      joinColumns = @JoinColumn(name = "salle_id")
    )
    @Column(name = "equipement")
    private List<String> equipements;
}
