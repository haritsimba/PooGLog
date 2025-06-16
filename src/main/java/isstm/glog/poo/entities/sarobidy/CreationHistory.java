package isstm.glog.poo.entities.sarobidy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CreationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    String nameStudent, firstNameStudent, dateDelivered, rgNumberStudent, typeOfCertificate, mentionStudent, levelStudent; 
}
