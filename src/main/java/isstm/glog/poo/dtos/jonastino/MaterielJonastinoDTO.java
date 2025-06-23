package isstm.glog.poo.dtos.jonastino;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MaterielJonastinoDTO {
    private Long id;
    private String nom;
    private String type;
    private int quantite;
    private String emplacement;
    // getters/setters
}