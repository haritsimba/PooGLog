package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.ClasseDto;
import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.enumerations.haritsimba.Parcours;
import isstm.glog.poo.mapper.haritsimba.ClasseMapper;
import isstm.glog.poo.repositories.haritsimba.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {
    @Autowired
    ClasseRepository classeRepository;

    public List<ClasseDto> findAll(){
        return ClasseMapper.mapListToDto(classeRepository.findAll());
    }

    public ClasseDto createClasse(ClasseDto dto) {
        Classe classe = new Classe();
        if (dto.getParcours() != null) {
            try {
                System.out.println("here");
                classe.setDepartment(Parcours.valueOf(dto.getParcours()));
                classe.setAutreParcours(null);
            } catch (IllegalArgumentException e) {
                System.out.println("not here");
                classe.setDepartment(null);
                classe.setAutreParcours(dto.getParcours());
            }
        }
        System.out.println("department : "+classe.getDepartment());
        System.out.println("autre parcours : "+classe.getAutreParcours());
        classe.setLevel(dto.getLevel());
        return ClasseMapper.mapToDto(classeRepository.save(classe));
    }
}
