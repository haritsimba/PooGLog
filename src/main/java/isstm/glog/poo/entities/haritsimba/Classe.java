    package isstm.glog.poo.entities.haritsimba;

    import isstm.glog.poo.entities.AbstractClasse;
    import isstm.glog.poo.enumerations.haritsimba.Niveau;
    import isstm.glog.poo.enumerations.haritsimba.Parcours;
    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Table(name = "classe")
    @ToString
    public class Classe extends AbstractClasse {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        Long id;
        @Enumerated(EnumType.STRING)
        Niveau level;
        @Enumerated(EnumType.STRING)
        Parcours department;

        String autreParcours; // Stocke "Finance", "Architecture", etc.

        @Override
        public String getParcours() {
            return department != null ? department.getLibelle() :
                    autreParcours != null ? autreParcours.toUpperCase() :
                            "";
        }

        @Override
        public String getNiveau() {
            return this.level.toString();
        }

        @Override
        public Long getClasseId(){
            return this.id;
        }
    }
