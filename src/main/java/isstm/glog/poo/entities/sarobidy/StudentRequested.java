package isstm.glog.poo.entities.sarobidy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequested { 

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    String name, firstName;
    
    String birthDate, birthPlace;

    String mention, level;

    String department, adress;

    String fatherName, motherName;

    String registrationNumber, sexe;


}
