package isstm.glog.poo.dtos.dina;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentDinaDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String programme;
}
