package isstm.glog.poo.entities;

import isstm.glog.poo.enumerations.haritsimba.Niveau;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractClass {

    protected Niveau niveau;
    protected String parcours;

}
