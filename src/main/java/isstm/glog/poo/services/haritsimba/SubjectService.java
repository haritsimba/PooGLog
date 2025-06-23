package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.request.SubjectCreateDto;
import isstm.glog.poo.dtos.haritsimba.response.SubjectDto;
import isstm.glog.poo.entities.haritsimba.Subject;
import isstm.glog.poo.mapper.haritsimba.SubjectMapper;
import isstm.glog.poo.repositories.haritsimba.ClasseRepository;
import isstm.glog.poo.repositories.haritsimba.SubjectRepository;
import isstm.glog.poo.repositories.haritsimba.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    TeacherRepository teacherRepository;
    public List<SubjectDto> findAll(){
        return SubjectMapper.mapListToDto(subjectRepository.findAll());
    }

    public List<SubjectDto> findByClasseId(Long classeId){
        List<Subject> all = subjectRepository.findAll();
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject subject : all){
            if(Objects.equals(SubjectMapper.mapToDto(subject).getClasseId(), classeId)){
                dtos.add(SubjectMapper.mapToDto(subject));
            }
        }
        return dtos;
    }

    public SubjectDto createSubject(SubjectCreateDto dto) {
        Subject subject = new Subject();
        subject.setName(dto.getSubjectName());
        subject.setClasse(classeRepository.findById(dto.getClasseId()).get());
        subject.setTeacher(teacherRepository.findById(dto.getTeacherId()).get());
        return SubjectMapper.mapToDto(subjectRepository.save(subject));
    }
}
