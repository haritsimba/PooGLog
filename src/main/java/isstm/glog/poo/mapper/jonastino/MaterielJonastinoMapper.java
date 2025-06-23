package isstm.glog.poo.mapper.jonastino;

import org.mapstruct.Mapper;
import isstm.glog.poo.entities.jonastino.MaterielJonastino;
import isstm.glog.poo.dtos.jonastino.MaterielJonastinoDTO;

@Mapper(componentModel = "spring")
public interface MaterielJonastinoMapper {
    MaterielJonastinoDTO toDTO(MaterielJonastino entity);
    MaterielJonastino toEntity(MaterielJonastinoDTO dto);
}
