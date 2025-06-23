package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractTimeSlot;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * L'entité principale pour l'emploi du temps
 * @author Franck
 */
@Entity
@Table(name = "time_slot")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeSlot extends AbstractTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalTime startTime;
    LocalTime endTime;
    DayOfWeek day;
}
