    package isstm.glog.poo.repositories.elio;

    import isstm.glog.poo.entities.elio.PreInscription;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface PreInscriptionRepository extends JpaRepository<PreInscription, Long> {
        @Query("SELECT p FROM PreInscription p WHERE p.informationsPersonnelles.cin = :cin")
        public Optional<PreInscription> findByCin(@Param("cin") String cin);
    }
