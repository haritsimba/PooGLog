package isstm.glog.poo.dtos.elio.response;

import lombok.Data;

@Data
public class ResultatOut {
    private String nom;
    private String prenom;
    private String cin;
    private String mention;
    private String parcours;
    private String statut;
    private Long date;
    private String frais;
    private String matricule;
}
