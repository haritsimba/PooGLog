package isstm.glog.poo.repositories.sarobidy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import isstm.glog.poo.entities.sarobidy.Certificate;

@Repository
public class CertificateRepository {

    private static final Map<String, Certificate> certificates = new HashMap<>();

    static{
        certificates.put("Certificat de Scolarité", new Certificate("src/main/resources/sarobidy/models/Certificat de Scolarite.pdf"));
        certificates.put("Attestation de réussite", new Certificate("src/main/resources/sarobidy/models/Attestation de reussite.pdf"));
    
    }

    public String findByType(String type){
        return certificates.get(type).getFilePath();
    }

}
