package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractSubject;
import jakarta.persistence.*;
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
@Table(name = "subject")
public class Subject extends AbstractSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String name;

    @Override
    public Long getSubjectId(){
        return this.id;
    }
}
