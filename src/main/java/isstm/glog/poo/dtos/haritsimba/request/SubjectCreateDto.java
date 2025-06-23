package isstm.glog.poo.dtos.haritsimba.request;

import lombok.Data;

@Data
public class SubjectCreateDto {
    Long teacherId;
    String subjectName;
    Long classeId;
}
