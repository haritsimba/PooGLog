package isstm.glog.poo.mapper.haritsimba;


import isstm.glog.poo.dtos.haritsimba.ClasseDto;
import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.enumerations.haritsimba.Parcours;

import java.util.ArrayList;
import java.util.List;

public class ClasseMapper {

    public static ClasseDto mapToDto(Classe classe){
        ClasseDto dto = new ClasseDto();
        dto.setId(classe.getClasseId());
        dto.setLevel(classe.getLevel());
        dto.setParcours(classe.getParcours());
        return dto;
    }

    public static List<ClasseDto> mapListToDto(List<Classe> classes){
        List<ClasseDto> dtos = new ArrayList<>();
        for (Classe c:classes){
            ClasseDto dto = mapToDto(c);
            dtos.add(dto);
        }
        return dtos;
    }
}
