package isstm.glog.poo.repositories.elio;

import isstm.glog.poo.entities.elio.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,String> {
}
