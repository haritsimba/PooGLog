package isstm.glog.poo.services.sarobidy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import isstm.glog.poo.dtos.sarobidy.request.CertificateRequest;
import isstm.glog.poo.entities.sarobidy.CreationHistory;
import isstm.glog.poo.entities.sarobidy.StudentRequested;
import isstm.glog.poo.repositories.sarobidy.CreationHistoryRepository;

@Service
public class CreationHistoryService {

    @Autowired
    CreationHistoryRepository creationHistoryRepository;

    @Autowired
    StudentRequestedService studentRequestedService;

    public long countLineOfCreationHistory(String RgNumber, String typeOfCertificate){

        return creationHistoryRepository.countByRgNumberStudentAndTypeOfCertificate(RgNumber, typeOfCertificate);
    }



    public Boolean verifyHistoryBeforeCreation(String type, String registrationNumber){
        
        int numberOfCreationRules = chooseRulesByType(type);
        long countOfHistory = countLineOfCreationHistory(registrationNumber, type);
        Boolean res = false;

        if(countOfHistory >= numberOfCreationRules){
            res = true;
        }

        return res;
    }



    public int chooseRulesByType(String type){
        Map<String, Integer> rulesList = new HashMap<> (); 

        rulesList.put("Certificat de Scolarité", 10000);
        rulesList.put("Attestation de réussite", 1);

        return rulesList.get(type);

    }

    public void addNewHistory(CertificateRequest req){
      
        Optional<StudentRequested> studentRqInfos = studentRequestedService.findStudentByRegistrationNumber(req.getRegistrationNumber());

        if(studentRqInfos != null){
            StudentRequested st = studentRqInfos.get();
            CreationHistory instanceH = new CreationHistory();

            instanceH.setId(null);
            instanceH.setNameStudent(st.getName());
            instanceH.setFirstNameStudent(st.getFirstName());
            instanceH.setDateDelivered(LocalDate.now().toString());
            instanceH.setRgNumberStudent(st.getRegistrationNumber());
            instanceH.setMentionStudent(st.getMention());
            instanceH.setLevelStudent(st.getLevel());
            instanceH.setTypeOfCertificate(req.getType());

            creationHistoryRepository.save(instanceH);
        }

    }

    public List<CreationHistory> getAllHistories() {
        return creationHistoryRepository.findAll();
    }
    

}
