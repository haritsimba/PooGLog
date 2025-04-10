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
git clone https://github.com/haritsimba/PooGLog.git
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

<code>
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
</code>

---
<h4>Note :</h4>

<ul>
<li>Il est préférable que chaque étudiant travail dans un package spécifique afin d'éviter les conflits, par exemple :</li>
<p>John→entités :</p> 
<code>
entities/                   # Entités JPA pour la base de données   
├── john/                   # Package spécifique à l'étudiant john pour ces entités             
│   ├── MonEntite1.java
│   ├──MonEntite2.java
</code><br>

<p>John→services :</p>
<code>
services/                   # Logique métier de l'application   
├── john/                   # Package spécifique à l'étudiant john pour ces services           
│   ├──MonService1.java
│   ├──MonService2.java
</code><br>

<li>Pour éviter les conflits sur GitHub, il est préférable que chaque étudiant possède sa propre branche</li>
<li>Veuillez suivre l'architecture du projet pour faciliter la fusion</li>
<li>Vous pouvez tester votre api avec <a href="https://www.postman.com/downloads/">Postman</a> ou <a href="https://www.usebruno.com/downloads">Bruno (Je vous le récommande)</a> ou via la documentation dans <code>http://localhost:votre_port/api-docs-ui.html</code></li>
</ul>

---
<h4>Références :</h4>
<ul>
    <li>Pour ceux qui ne sont pas familiarisés avec Spring Boot, je vous recommande <a href="https://www.youtube.com/watch?v=k6Nmt-l1Bzc&list=PLbZw3gn9fSnTs4IZMCzTPjNJ5cmP4_HqZ">cette chaîne YouTube</a></li>
    <li>Pour ceux qui n'ont jamais utilisé lombok : <a href="https://www.baeldung.com/intro-to-project-lombok">pour éviter les tâches répétitives (constructeur,getters,setters,...)</a></li>
</ul>
