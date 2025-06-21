package isstm.glog.poo.services.sarobidy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isstm.glog.poo.dtos.sarobidy.response.SignatoryResponse;
import isstm.glog.poo.entities.sarobidy.StudentRequested;
import isstm.glog.poo.repositories.sarobidy.CertificateRepository;
import isstm.glog.poo.utils.sarobidy.StudentRequestedUtils;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    StudentRequestedService studentRequestedService;

    @Autowired
    StudentRequestedUtils studentRequestedUtils;

    @Autowired
    SignatoryService signatoryService;

    @Autowired
    DeliveredCertificateCountService deliveredCertificateCountService;

    @Autowired
    CreationHistoryService creationHistoryService;

// Méthode de la génération du certificat
    public byte[] createNewCertificate(String type, String registrationNumber){

        String pathOfModel = certificateRepository.findByType(type);
        Optional<StudentRequested> opt = studentRequestedService.findStudentByRegistrationNumber(registrationNumber);
       

        try{
            File model = new File(pathOfModel);
            deliveredCertificateCountService.incrementNumberOfCertificateDelivered();
            return getAndSetFieldsInPdf(type, model, opt);
        }
        catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
        
    } 
// *************************************** //


// Méthode pour avoir le nom, prénom, grade du signateur du certificat ou attestation
    private SignatoryResponse getSignatoryByType(String type){

        Map<String, String> listPosteClef = Map.of(
            "Certificat de Scolarité", "Chef de la scolarité",
            "Attestation de réussite", "Directeur de l'ISSTM"
        );
    
        return signatoryService.findChiefByPoste(listPosteClef.getOrDefault(type, "Inconnu"));
    }
//*******************************************************************************//



// Méthode pour avoir tous les champs du modèle à remplir
    private List<String> getModelChampsByType(String type){
        return switch(type){
            case "Certificat de Scolarité" -> List.of("ref_certificat", "name_chef_scol", "name_firstname", "date_birth", "father_name", "mother_name", "mention", "department", "level" ,"registration_number" ,"year_univ");
            case "Attestation de réussite" -> List.of(
                "year_univ", 
                "ref_certificat_attestation", 
                "label_mr_ms", 
                "name", 
                "label_date_birth", 
                "date_birth", 
                "place_birth", 
                "registration_number", 
                "name_diplome" ,
                "mention",
                "department",
                "mention_note",
                "date_now"
            );

            default -> List.of();
        };            
    }
// ****************************************************** //


// Méthode qui retourne le pdf après remplissage 
    private byte[] getAndSetFieldsInPdf(String type, File modelPdf, Optional<StudentRequested> optional_student){

        SignatoryResponse signatoryChief = getSignatoryByType(type);
        Map<String, String> infosOfStudent = studentRequestedUtils.formateInfosOfStudentRequested(type, optional_student, signatoryChief);
        List<String> champsModel = getModelChampsByType(type);
       

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PDDocument document = PDDocument.load(modelPdf);
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
                
            if(acroForm != null){
                   
                for(String champ : champsModel){
                    PDField field = acroForm.getField(champ);
                    if (field == null) {
                        throw new RuntimeException("⚠ Champ introuvable dans le PDF : " + champ);
                    }

                    String valeur = infosOfStudent.get(champ);
                    if (valeur == null) {
                        throw new RuntimeException("⚠ Valeur manquante pour le champ : " + champ);
                    }

                    field.setValue(valeur);
                }
        
                document.save(outputStream);
                document.close();
            }

            return outputStream.toByteArray();
        }
        catch(Exception e){
            throw new RuntimeException("Erreur lors du remplissage du PDF", e);
        }
       
    }
// ************************************************************** //

}
