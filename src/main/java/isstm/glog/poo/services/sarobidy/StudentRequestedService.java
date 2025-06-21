package isstm.glog.poo.services.sarobidy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isstm.glog.poo.entities.sarobidy.StudentRequested;
import isstm.glog.poo.repositories.sarobidy.StudentRequestedRepository;

@Service
public class StudentRequestedService {

    @Autowired
    StudentRequestedRepository studentRequestedRepository;

    public Optional<StudentRequested> findStudentByRegistrationNumber(String registrationNumber){

        Optional<StudentRequested> infosOfStudent = studentRequestedRepository.findByRegistrationNumber(registrationNumber);
        return infosOfStudent;
    }
}
