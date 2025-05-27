package isstm.glog.poo.services.elio;

import isstm.glog.poo.dtos.elio.request.PreInscriptionRequest;
import isstm.glog.poo.dtos.elio.response.PreInscriptionResponse;
import isstm.glog.poo.entities.elio.*;
import isstm.glog.poo.repositories.elio.PreInscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreInscriptionService {

    @Autowired
    private PreInscriptionRepository repository;

    public PreInscription savePreInscription(PreInscription preInscription) {
        return repository.save(preInscription);
    }

    public PreInscription fromDto(PreInscriptionRequest dto, Documents docs) {
        PreInscription preInscription =  new PreInscription();

        // Info persos
        InformationsPersonnelles entity = new InformationsPersonnelles();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setTelephoneAlt(dto.getTelephoneAlt());

        //etat civil
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setLieuNaissance(dto.getLieuNaissance());
        entity.setCin(dto.getCin());
        entity.setPays(dto.getPays());
        entity.setNationalite(dto.getNationalite());
        entity.setGenre(dto.getGenre());
        entity.setSituationMatrimoniale(dto.getSituationMatrimoniale());
        entity.setSalarie(dto.getSalarie());

        // Parents
        Parents parents = new Parents();
        parents.setNomPere(dto.getNomPere());
        parents.setProfessionPere(dto.getProfessionPere());
        parents.setNomMere(dto.getNomMere());
        parents.setProfessionMere(dto.getProfessionMere());
        parents.setAdresseParents(dto.getAdresseParents());
        parents.setTelephoneParents(dto.getTelephoneParents());
        parents.setTelephoneParentsAlt(dto.getTelephoneParentsAlt());
        parents.setPaysParents(dto.getPaysParents());
        parents.setRegionParents(dto.getRegionParents());

        //ParcoursAcademique

        ParcoursAcademique PA = new ParcoursAcademique();
        PA.setAnneeBac(dto.getAnneeBac());
        PA.setNumeroInscription(dto.getNumeroInscription());
        PA.setSerieBac(dto.getSerieBac());
        PA.setMentionBac(dto.getMentionBac());
        PA.setUniversiteDelivrance(dto.getUniversiteDelivrance());
        PA.setInscriptionAnterieure(dto.getInscriptionAnterieure());
        PA.setUniversiteAnterieure(dto.getUniversiteAnterieure());
        PA.setEtablissementAnterieur(dto.getEtablissementAnterieur());
        PA.setParcoursAnterieur(dto.getParcoursAnterieur());

        // Choix de formation
        PA.setNiveau(dto.getNiveau());
        PA.setMention(dto.getMention());
        PA.setParcours(dto.getParcours());



        preInscription.setInformationsPersonnelles(entity);
        preInscription.setParents(parents);
        preInscription.setParcoursAcademique(PA);
        preInscription.setDocuments(docs);
        preInscription.setStatus(Status.EN_ATTENTE);
         return preInscription;
    }

    public PreInscriptionResponse toDto(PreInscription entity) {
        PreInscriptionResponse response = new PreInscriptionResponse();
        response.setNom(entity.getInformationsPersonnelles().getNom());
        response.setPrenom(entity.getInformationsPersonnelles().getPrenom());
        response.setEmail(entity.getInformationsPersonnelles().getEmail());
        response.setTelephone(entity.getInformationsPersonnelles().getTelephone());
        response.setDateNaissance(entity.getInformationsPersonnelles().getDateNaissance());
        response.setStatus(entity.getStatus());
        return response;
    }

    public PreInscription updateStatus(Long id, Status s) {
        Optional<PreInscription> p = repository.findById(id);
        if (p.isPresent()){
            PreInscription preInscription = p.get();
            preInscription.setStatus(s);
            return repository.save(preInscription);
        }else{
            return null;
        }
    }
}
