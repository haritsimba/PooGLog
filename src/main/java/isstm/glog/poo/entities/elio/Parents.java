package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomPere;
    private String professionPere;
    private String contactPere;

    private String nomMere;
    private String professionMere;
    private String contactMere;

    private String adresseParents;
    private String regionParents;
    private String paysParents;
    private String telephoneParents;
    private String telephoneParentsAlt;

}
