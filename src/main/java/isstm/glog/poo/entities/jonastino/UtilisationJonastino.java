package isstm.glog.poo.entities.jonastino;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "utilisations")
public class UtilisationJonastino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materiel_id")
    private MaterielJonastino materiel;

    private String utilisateur;
    private LocalDateTime debut;
    private LocalDateTime fin;
}