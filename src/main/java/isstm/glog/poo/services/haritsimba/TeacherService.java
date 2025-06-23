package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.TeacherDto;
import isstm.glog.poo.entities.haritsimba.Teacher;
import isstm.glog.poo.mapper.haritsimba.TeacherMapper;
import isstm.glog.poo.repositories.haritsimba.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public List<TeacherDto> findAll(){
        return TeacherMapper.mapListToDto(teacherRepository.findAll());
    }

    public TeacherDto create(TeacherDto dto){
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        return TeacherMapper.mapToDto(teacherRepository.save(teacher));
    }
}
