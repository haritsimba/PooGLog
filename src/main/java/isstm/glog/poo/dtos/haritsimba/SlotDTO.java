package isstm.glog.poo.dtos.haritsimba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO {
    private boolean skip;
    private int rowSpan;
    private String subjectName;
    private String teacherName;
}
