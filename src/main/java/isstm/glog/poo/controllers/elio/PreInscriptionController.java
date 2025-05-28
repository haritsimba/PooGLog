package isstm.glog.poo.controllers.elio;

import isstm.glog.poo.dtos.elio.request.UpdateStatusRequest;
import isstm.glog.poo.dtos.elio.response.FileUploadOut;
import isstm.glog.poo.dtos.elio.response.PreInscriptionResponse;
import isstm.glog.poo.dtos.elio.request.PreInscriptionRequest;
import isstm.glog.poo.entities.elio.Documents;
import isstm.glog.poo.entities.elio.PreInscription;
import isstm.glog.poo.repositories.elio.PreInscriptionRepository;
import isstm.glog.poo.services.elio.FileService;
import isstm.glog.poo.services.elio.PreInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/preinscription")
@CrossOrigin(origins = "*")
public class PreInscriptionController {

    @Autowired
    private PreInscriptionService service;
    @Autowired
    private FileService fileService;

    @Autowired
    PreInscriptionRepository preInscriptionRepository;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PreInscriptionResponse> submitForm(
            @RequestPart("data") PreInscriptionRequest dto,
            @RequestPart(value = "photoIdentite", required = false) MultipartFile photo,
            @RequestPart(value = "copieDiplome", required = false) MultipartFile diplome,
            @RequestPart(value = "releveNotes", required = false) MultipartFile releve,
            @RequestPart(value = "recuPaiement", required = false) MultipartFile recu,
            @RequestPart(value = "acteNaissance", required = false) MultipartFile acte,
            @RequestPart(value = "certificatResidence", required = false) MultipartFile certificat,
            @RequestPart(value = "enveloppesTimbrees", required = false) MultipartFile enveloppe
    ) throws IOException {

        String basePath = "uploads/";
        System.out.println(dto.toString());

        Documents docs = new Documents();

        FileUploadOut photoOut = fileService.saveFile(photo);
        FileUploadOut diplomeOut = fileService.saveFile(diplome);
        FileUploadOut releveOut = fileService.saveFile(releve);
        FileUploadOut recuOut = fileService.saveFile(recu);
        if(acte!=null){
            FileUploadOut acteOut = fileService.saveFile(acte);
            docs.setActeNaissancePath(acteOut != null ? acteOut.fileName() : null);
        }

        if(certificat!=null){
            FileUploadOut certificatOut = fileService.saveFile(certificat);
            docs.setCertificatResidencePath(certificatOut != null ? certificatOut.fileName() : null);
        }

        if(enveloppe!=null){
            FileUploadOut enveloppeOut = fileService.saveFile(enveloppe);
            docs.setEnveloppesTimbreesPath(enveloppeOut != null ? enveloppeOut.fileName() : null);
        }




        docs.setPhotoIdentitePath(photoOut != null ? photoOut.fileName() : null);
        docs.setCopieDiplomePath(diplomeOut != null ? diplomeOut.fileName() : null);
        docs.setReleveNotesPath(releveOut != null ? releveOut.fileName() : null);
        docs.setRecuPaiementPath(recuOut != null ? recuOut.fileName() : null);


        PreInscription entity = service.fromDto(dto,docs);

        // Sauvegarder les fichiers et assigner les chemins dans 'documents'
        if (entity.getDocuments() == null) {
            entity.setDocuments(new Documents()); // Assure-toi que 'documents' est initialisé
        }

        PreInscription saved = service.savePreInscription(entity);
        PreInscriptionResponse response = service.toDto(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getPreinscriptions(){
        List<PreInscription> preInscriptions = preInscriptionRepository.findAll();
        for (PreInscription preInscription:preInscriptions){
            System.out.println(preInscription.toString());
        }
        return ResponseEntity.ok(preInscriptions);
    }

    private String saveFile(MultipartFile file, String basePath) throws IOException {
        if (file != null && !file.isEmpty()) {
            Path dir = Paths.get(basePath);
            if (!Files.exists(dir)) Files.createDirectories(dir);
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = dir.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        }
        return null;
    }

    @PutMapping("/{id}")
    public PreInscriptionResponse updateStatus(
            @PathVariable(name = "id", value = "id") Long id,
            @RequestBody  UpdateStatusRequest statusRequest) {
        return service.toDto(service.updateStatus(id, statusRequest.getStatus()));
    }

}
