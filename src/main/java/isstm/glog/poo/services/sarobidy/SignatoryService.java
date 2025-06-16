package isstm.glog.poo.services.sarobidy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isstm.glog.poo.dtos.sarobidy.response.SignatoryResponse;
import isstm.glog.poo.entities.sarobidy.Signatory;
import isstm.glog.poo.repositories.sarobidy.SignatoryRepository;

@Service
public class SignatoryService {

    @Autowired
    SignatoryRepository signatoryRepository;

    public SignatoryResponse findChiefByPoste(String poste){

        SignatoryResponse response = new SignatoryResponse();
        Optional<Signatory> optionalChief = signatoryRepository.findByPoste(poste);

        if(optionalChief != null){
            Signatory chief = optionalChief.get();

            response.setName(chief.getName());
            response.setFirstName(chief.getFirstName());
            response.setGrade(chief.getGrade());
        }
        
        return response;
    }
}
