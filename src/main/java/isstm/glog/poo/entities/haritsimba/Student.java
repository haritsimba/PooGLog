package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractStudent;
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
public class Student extends AbstractStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    String name;
}
