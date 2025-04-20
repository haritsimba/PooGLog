    package isstm.glog.poo.entities.haritsimba;

    import isstm.glog.poo.entities.AbstractClasse;
    import isstm.glog.poo.enumerations.haritsimba.Niveau;
    import isstm.glog.poo.enumerations.haritsimba.Parcours;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Classe extends AbstractClasse {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        Long id;
        @Enumerated(EnumType.STRING)
        Niveau level;
        @Enumerated(EnumType.STRING)
        Parcours department;

        @Override
        public String getNiveau() {
            return this.level.toString();
        }

        @Override
        public String getParcours() {
            return this.department.toString();
        }
    }
