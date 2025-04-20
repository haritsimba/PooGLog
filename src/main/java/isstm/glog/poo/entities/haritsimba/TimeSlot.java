package isstm.glog.poo.entities.haritsimba;

import isstm.glog.poo.entities.AbstractTimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class TimeSlot extends AbstractTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    LocalTime startTime;
    LocalTime endTime;
    DayOfWeek day;
}
