package isstm.glog.poo.dtos.haritsimba;

import isstm.glog.poo.enumerations.haritsimba.Niveau;
import isstm.glog.poo.enumerations.haritsimba.Parcours;
import lombok.Data;

import java.util.logging.Level;

@Data
public class ClasseDto {
    private Long id;
    private Niveau level;
    private String parcours;

}
