package isstm.glog.poo.utils.sarobidy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isstm.glog.poo.dtos.sarobidy.response.SignatoryResponse;
import isstm.glog.poo.entities.sarobidy.StudentRequested;
import isstm.glog.poo.services.sarobidy.DeliveredCertificateCountService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class StudentRequestedUtils {

    @Autowired
    DeliveredCertificateCountService deliveredCertificateCountService;

    public Map<String, String> formateInfosOfStudentRequested(Optional<StudentRequested> optional, SignatoryResponse chiefResponse){

        StudentRequested studentRequested_infos = optional.orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));

        String year_univ = LocalDate.now().minusYears(1).getYear() + "-" + LocalDate.now().getYear();

        int certificateNumber = deliveredCertificateCountService.getCertificateDeliveredCount();

        Map<String, String> infos = new HashMap<>();

        infos.put("name", studentRequested_infos.getName());
        infos.put("firstname", studentRequested_infos.getFirstName());
        infos.put("name_firstname", studentRequested_infos.getName()+" "+studentRequested_infos.getFirstName());
        infos.put("date_birth", studentRequested_infos.getBirthDate()+" à "+studentRequested_infos.getBirthPlace());
        infos.put("father_name", studentRequested_infos.getFatherName());
        infos.put("mother_name", studentRequested_infos.getMotherName());
        infos.put("mention", studentRequested_infos.getMention());
        infos.put("level", studentRequested_infos.getLevel());
        infos.put("department", studentRequested_infos.getDepartment());
        infos.put("registration_number", studentRequested_infos.getRegistrationNumber());
        infos.put("name_chef_scol", chiefResponse.getGrade()+" "+chiefResponse.getName()+" "+chiefResponse.getFirstName()+",");
        infos.put("year_univ", year_univ+".");
        infos.put("ref_certificat", "N°:"+certificateNumber+ "- 24/UMG/ISSTM/Dir./Scol.");

        return infos;

    } 
}
