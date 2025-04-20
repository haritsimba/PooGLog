package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractSubject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subject")
@Getter
@Setter
public class Subject extends AbstractSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @NotNull
    String name;
}
