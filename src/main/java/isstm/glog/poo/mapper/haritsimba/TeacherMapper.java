package isstm.glog.poo.mapper.haritsimba;

import isstm.glog.poo.dtos.haritsimba.TeacherDto;
import isstm.glog.poo.entities.haritsimba.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherMapper {
    public static TeacherDto mapToDto(Teacher teacher){
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getTeacherId());
        dto.setName(teacher.getName());
        return dto;
    }

    public static List<TeacherDto> mapListToDto(List<Teacher> teachers){
        List<TeacherDto> dtos = new ArrayList<>();
        for (Teacher t: teachers){
            dtos.add(mapToDto(t));
        }
        return dtos;
    }
}
