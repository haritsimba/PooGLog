package isstm.glog.poo.mapper.haritsimba;

import isstm.glog.poo.dtos.haritsimba.response.SubjectDto;
import isstm.glog.poo.entities.haritsimba.Subject;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper {
    public static SubjectDto mapToDto(Subject subject){
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getSubjectId());
        dto.setName(subject.getName());
        dto.setClasseId(subject.getClasse().getClasseId());
        return dto;
    }

    public static List<SubjectDto> mapListToDto(List<Subject> subjects){
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject s : subjects){
            SubjectDto dto = mapToDto(s);
            dtos.add(dto);
        }
        return dtos;
    }
}
