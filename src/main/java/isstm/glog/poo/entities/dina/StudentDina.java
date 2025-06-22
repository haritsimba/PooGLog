package isstm.glog.poo.entities.dina;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "StudentDina")
@Table(name = "students_q10_dina")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate birthDate;

    @Column(nullable = false)
    private String programme;  // ex. "M1 Génie Logiciel"
}
