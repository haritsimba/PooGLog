package isstm.glog.poo.entities.sarobidy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DeliveredCertificateCount {
    
    @Id
    private Long id = 1L;

    @Column(nullable = false)
    private int numberOfCertificateDelivered = 0;
}
