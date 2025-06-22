package isstm.glog.poo.dtos.dina;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalleDinaDto {
    private Long id;
    private String nom;
    private int capacite;
    private String batiment;
    private String etage;
    private List<String> equipements;
}
