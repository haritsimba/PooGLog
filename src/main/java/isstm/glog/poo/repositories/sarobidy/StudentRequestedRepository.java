package isstm.glog.poo.repositories.sarobidy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isstm.glog.poo.entities.sarobidy.StudentRequested;

public interface StudentRequestedRepository extends JpaRepository<StudentRequested,Long> {

    Optional<StudentRequested> findByRegistrationNumber(String registrationNumber);
}
