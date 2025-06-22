package isstm.glog.poo.entities.dina;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "ReservationDina")
@Table(name = "reservations_q10_dina")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime debut;

    @Column(nullable = false)
    private LocalDateTime fin;

    @Column(nullable = false)
    private String organisateur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salle_id")
    private SalleDina salle;
}
