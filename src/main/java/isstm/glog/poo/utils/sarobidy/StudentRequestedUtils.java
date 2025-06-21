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

    public Map<String, String> formateInfosOfStudentRequested(String type, Optional<StudentRequested> optional, SignatoryResponse chiefResponse){

        StudentRequested studentRequested_infos = optional.orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));

        String year_univ = LocalDate.now().minusYears(1).getYear() + "-" + LocalDate.now().getYear();

        int certificateNumber = deliveredCertificateCountService.getCertificateDeliveredCount();

        Map<String, String> infos = new HashMap<>();
        Map<String, String> mentionSign = new HashMap<>();

        mentionSign.put("STNPA", "SCIENCES ET TECHNIQUES DU NUMERIQUES ET PHYSIQUES APPLIQUEES");
        mentionSign.put("STGCI", "SCIENCES ET TECHNIQUES DU NUMERIQUES ET PHYSIQUES APPLIQUEES");
        mentionSign.put("STI", "SCIENCES ET TECHNIQUES DU NUMERIQUES ET PHYSIQUES APPLIQUEES");



        infos.put("firstname", studentRequested_infos.getFirstName());
        infos.put("name", studentRequested_infos.getName()+" "+studentRequested_infos.getFirstName());
        infos.put("date_birth", (type.equals("Certificat de Scolarité")) ? studentRequested_infos.getBirthDate()+" à "+studentRequested_infos.getBirthPlace() : studentRequested_infos.getBirthDate());
        infos.put("father_name", studentRequested_infos.getFatherName());
        infos.put("mother_name", studentRequested_infos.getMotherName());
        infos.put("mention", studentRequested_infos.getMention());
        infos.put("place_birth", studentRequested_infos.getBirthPlace());
        infos.put("level", studentRequested_infos.getLevel());
        infos.put("department", studentRequested_infos.getDepartment());
        infos.put("registration_number", studentRequested_infos.getRegistrationNumber());
        infos.put("name_chef_scol", chiefResponse.getGrade()+" "+chiefResponse.getName()+" "+chiefResponse.getFirstName()+",");
        infos.put("year_univ", (type == "Certificat de Scolarité") ? year_univ+"." : year_univ);
        infos.put("ref_certificat", "N°:"+certificateNumber+ "- "+LocalDate.now().getYear()+"/UMG/ISSTM/Dir./Scol.");
        infos.put("ref_certificat_attestation", "N°:      "+"/"+LocalDate.now().getYear()+"/"+studentRequested_infos.getDepartment()+"/ISSTM/UMG/DIR");
        infos.put("sexe", studentRequested_infos.getSexe());
        infos.put("label_mr_ms", (studentRequested_infos.getSexe().equals("M")) ? "Monsieur :" : "Madame :");
        infos.put("label_date_birth", (studentRequested_infos.getSexe().equals("M")) ? "Né le :" : "Née le :");
        infos.put("date_now", LocalDate.now().toString());
        infos.put("name_diplome",
            switch(studentRequested_infos.getLevel()){
                case "L2" -> "DIPLÔME DE DTS EN " + mentionSign.get(studentRequested_infos.getMention().toString());
                case "L3" -> "DIPLÔME DE LICENCE EN "+ mentionSign.get(studentRequested_infos.getMention().toString());
                case "M1" -> "DIPLÔME DE MASTER I EN "+ mentionSign.get(studentRequested_infos.getMention().toString());
                case "M2" -> "DIPLÔME DE MASTER II EN "+mentionSign.get(studentRequested_infos.getMention().toString());
                default -> "";
            }

        );
        infos.put("mention_note", "Assez-bien");


        return infos;

    } 
}
