package isstm.glog.poo.dtos.elio.request;

import isstm.glog.poo.entities.elio.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class PreInscriptionRequest {
    // Getters et setters
    //Information perso
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String telephoneAlt;

    //Etat civil
    private LocalDate dateNaissance;
    private  String lieuNaissance;
    private String cin;
    private String pays;
    private String nationalite;
    private String genre;
    private String situationMatrimoniale;
    private String salarie;

    //Parents
    private String nomPere;
    private String professionPere;
    private String nomMere;
    private String professionMere;
    private String adresseParents;
    private String regionParents;
    private String paysParents;
    private String telephoneParents;
    private String telephoneParentsAlt;

    //etude
    private String anneeBac;
    private String numeroInscription;
    private String serieBac;
    private String mentionBac;
    private String universiteDelivrance;
    private String inscriptionAnterieure;
    private String universiteAnterieure;
    private String etablissementAnterieur;
    private String parcoursAnterieur;

    //chois de formation
    private String niveau;
    private String mention;
    private String parcours;
    private Status status;   // doit valoir ACCEPTE ou REFUSE



}
