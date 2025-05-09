package isstm.glog.poo.entities.haritsimba;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
public class StTest {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String name;
    String prenom;
}
