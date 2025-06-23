package isstm.glog.poo.dtos.jonastino;


import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisationJonastinoDTO {
    private Long id;
    private Long materielId;
    private String utilisateur;
    private LocalDateTime debut;
    private LocalDateTime fin;
    // getters/setters
}