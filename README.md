# 💾 PooGLog

<h4>Description :</h4>
<p>Devoir POO Génie Logiciel — partie backend</p><br>

---
<h4>Technos :</h4>

<ul>
    <li>Stack : Spring Boot (REST API)</li>
    <li>Base de données : MariaDB ou MySQL</li>
</ul>

---

<h4>Prérequis :</h4>
<ul>
    <li><a href="https://git-scm.com/downloads">Git</a></li>
    <li><a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java 17+</a></li>
    <li><a href="https://maven.apache.org/download.cgi">Maven</a> (facultatif, car Maven Wrapper est inclus)</li>
    <li>Une base de données MySQL ou MariaDB</li>
</ul>

---

<h4>Initialisation :</h4>

<ol><li>Cloner le projet :</li></ol>

```bash
git clone https://github.com/ton-utilisateur/PooGLog.git
cd PooGLog
```

<ol start="2"> <li>Créer un fichier <code>application.yaml</code> dans le dossier <code>src/main/resources/</code></li> <li>Copier le contenu de <code>application.yaml.example</code> dans <code>application.yaml</code> et ajuster selon votre configuration</li> </ol>
<ol start="4"><li>Installer les dépendances :</li></ol>

✅ Linux / macOS

```bash
chmod +x mvnw
./mvnw clean install
```

🪟 Windows
```bash
mvnw.cmd clean install
```
<ol start="5"> <li>Lancer votre projet :</li> </ol>

✅ Linux / macOS
```bash
./mvnw spring-boot:run
```

🪟 Windows
```bash
mvnw.cmd spring-boot:run
```

---

<h4>Structure du projet :</h4>

PooGLog/
├── src/
│   ├── main/
│   │   ├── java/                        
│   │   │   └── isstm/
│   │   │       └── glog/
│   │   │           └── poo/
│   │   │               ├── configurations/          # Configuration des beans et autres
│   │   │               ├── controllers/             # Contrôleurs Spring (API REST)
│   │   │               ├── dtos/                   # Objets de transfert de données
│   │   │               ├── entities/               # Entités JPA pour la base de données
│   │   │               ├── PooApplication.java     # Point d'entrée de l'application Spring Boot
│   │   │               ├── repositories/           # Interfaces pour l'accès aux données (JPA)
│   │   │               ├── services/               # Logique métier de l'application
│   │   │               └── utils/                  # Fonctions utilitaires
|   |   |               └── enumerations/           # Énumérations
│   │   └── resources/
│   │       ├── application.yaml         # Config personnalisée
│   │       └── application.yaml.example # Exemple de config
│   └── test/                          # Tests unitaires
├── mvnw, mvnw.cmd                     # Maven Wrapper (Windows & Linux)
├── pom.xml                            # Dépendances du projet

---
<h4>Note :</h4>

<ul>
    <li>Il est préférable que chaque étudiant ajoute un préfixe pour ses classes afin d'éviter les conflits, par exemple :</li>
    <p>Franck : <code>FrNomDeMaClasse</code>, <code>FrMonEntite</code></p>
    <p>John : <code>JoNomDeMaClasse</code>, <code>JoMonEntite</code></p>
    <li>Pour éviter les conflits sur GitHub, il est préférable que chaque étudiant possède sa propre branche</li>
    <li>Veuillez suivre l'architecture du projet pour faciliter la fusion</li>
</ul>

---
<h4>Références :</h4>
<ul>
    <li>Pour ceux qui ne sont pas familiarisés avec Spring Boot, je vous recommande <a href="https://www.youtube.com/watch?v=k6Nmt-l1Bzc&list=PLbZw3gn9fSnTs4IZMCzTPjNJ5cmP4_HqZ">cette chaîne YouTube</a></li>
</ul>
