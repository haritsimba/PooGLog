package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractTeacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends AbstractTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @NotNull
    String name;
    @Override
    public Long getTeacherId(){
        return this.id;
    }
}
