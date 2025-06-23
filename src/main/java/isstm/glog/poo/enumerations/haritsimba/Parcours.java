package isstm.glog.poo.enumerations.haritsimba;

public enum Parcours {
    GINFO("Génie Informatique"),
    BTP("Génie Civil BTP"),
    GLOG("Génie Logistique"),
    GEI("Génie Electronique Informatique"),
    GTL("Génie Télécommunications"),
    GBM("Génie Biomédical"),
    GL("Génie Logiciel");

    private final String libelle;

    Parcours(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle.toUpperCase(); // Pour avoir "GÉNIE INFORMATIQUE" en majuscules
    }
}
