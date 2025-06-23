package isstm.glog.poo.mapper.jonastino;

import org.mapstruct.Mapper;
import isstm.glog.poo.entities.jonastino.MaterielJonastino;
import isstm.glog.poo.entities.jonastino.UtilisationJonastino;
import isstm.glog.poo.dtos.jonastino.UtilisationJonastinoDTO;

@Mapper(componentModel = "spring")
public interface UtilisationJonastinoMapper {

    default UtilisationJonastinoDTO toDTO(UtilisationJonastino entity) {
        if (entity == null) return null;
        return UtilisationJonastinoDTO.builder()
            .id(entity.getId())
            .materielId(entity.getMateriel().getId())
            .utilisateur(entity.getUtilisateur())
            .debut(entity.getDebut())
            .fin(entity.getFin())
            .build();
    }

    default UtilisationJonastino toEntity(UtilisationJonastinoDTO dto) {
        if (dto == null) return null;
        UtilisationJonastino u = new UtilisationJonastino();
        u.setId(dto.getId());
        u.setMateriel(MaterielJonastino.builder().id(dto.getMaterielId()).build());
        u.setUtilisateur(dto.getUtilisateur());
        u.setDebut(dto.getDebut());
        u.setFin(dto.getFin());
        return u;
    }
}
