package isstm.glog.poo.repositories.sarobidy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isstm.glog.poo.entities.sarobidy.Signatory;

public interface SignatoryRepository extends JpaRepository<Signatory,Long>{

    Optional<Signatory> findByPoste(String poste);

}
