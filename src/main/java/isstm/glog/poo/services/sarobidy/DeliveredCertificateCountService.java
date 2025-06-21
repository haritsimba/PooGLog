package isstm.glog.poo.services.sarobidy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isstm.glog.poo.entities.sarobidy.DeliveredCertificateCount;
import isstm.glog.poo.repositories.sarobidy.DeliveredCertificateCountRepository;

@Service
public class DeliveredCertificateCountService {

    @Autowired
    DeliveredCertificateCountRepository deliveredCertificateCountRepository;
    

    public void incrementNumberOfCertificateDelivered(){

        DeliveredCertificateCount countEntity = deliveredCertificateCountRepository.findById(1L)
        .orElseGet(() -> deliveredCertificateCountRepository.save(new DeliveredCertificateCount()));

        countEntity.setNumberOfCertificateDelivered(countEntity.getNumberOfCertificateDelivered() + 1);
        deliveredCertificateCountRepository.save(countEntity);

    }


    public int getCertificateDeliveredCount(){
        return deliveredCertificateCountRepository.findById(1L)
               .map(DeliveredCertificateCount :: getNumberOfCertificateDelivered)
               .orElse(0);
    }
}
