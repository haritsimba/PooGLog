package isstm.glog.poo.mapper.dina;

import org.mapstruct.Mapper;
import isstm.glog.poo.entities.dina.SalleDina;
import isstm.glog.poo.dtos.dina.SalleDinaDto;

@Mapper(componentModel = "spring")
public interface SalleDinaMapper {
    SalleDinaDto toDto(SalleDina entity);
    SalleDina toEntity(SalleDinaDto dto);
}
