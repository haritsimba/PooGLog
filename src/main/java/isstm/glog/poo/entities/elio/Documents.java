package isstm.glog.poo.entities.elio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoIdentitePath;
    private String copieDiplomePath;
    private String releveNotesPath;
    private String recuPaiementPath;
    private String acteNaissancePath;
    private String certificatResidencePath;
    private String enveloppesTimbreesPath;
}
