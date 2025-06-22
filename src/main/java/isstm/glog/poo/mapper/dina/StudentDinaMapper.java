package isstm.glog.poo.mapper.dina;

import org.mapstruct.Mapper;
import isstm.glog.poo.entities.dina.StudentDina;
import isstm.glog.poo.dtos.dina.StudentDinaDto;

@Mapper(componentModel = "spring")
public interface StudentDinaMapper {
    StudentDinaDto toDto(StudentDina entity);
    StudentDina toEntity(StudentDinaDto dto);
}
