# Domotique – Application de Gestion Domotique

## Présentation

**Domotique** est une application complète permettant de gérer une habitation connectée à l'aide d'objets, d'automatisations et de leurs interactions.

**Fonctionnalités principales :**
* **Paramétrer et utiliser les capteurs**
* **Administrer des automatisations et simuler des scénarios**
* **Consulter l’état de pièces grâce à une visualisation 2D**

---

## Technologies Utilisées

Ce projet repose sur une architecture 3-tiers standard et robuste.

### Frontend
* **React** : Framework
* **JavaScript** : Langage

### Backend
* **Spring Boot**  : Framework
* **Java** : Langage

### Base de données
**PostgreSQL**

### Plateforme d'IC/CD
**GitHub Actions**

---

## Prérequis

* [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Apache Maven](https://maven.apache.org/)
* [Node.js](https://nodejs.org/)
* [PostgreSQL](https://www.postgresql.org/)

---

# Auteurs

* **Axel JACOMME**
  * M1 - Mock permettant de générer des données pour un capteur de température -- V5 dev
  * M2 - Mock permettant de générer des données pour un capteur de mouvement -- V5 dev
  * M3 - Mock permettant de générer des données pour un capteur de température intérieure -- V5 dev
  * M4 - Mock pour le degré d'ouverture d'une fenêtre
  * M5 - Mock de capteur défectueux 
  * M6 - Mock de dépassement de seuil
  * M7 - Générateur d'historique de données
  * M8 - Mock pour le niveau d'intensité d'une lampe
    
  * A1 - Détection des anomalies des mesures des capteurs de température -- V2 dev, pas dans master car déclaré pas intéressant
  * A2 - Création d'interprétations pour chaque type de données
    
  * S1 - Recherche d'une solution adaptée pour afficher des données sur un graphique -- fait
    
  * US1 – Visualiser l'état et la valeur en temps réel -- dev
  * US2 – Consulter l'historique des mesures -- V2 dev
  * US3 – Recevoir des notifications d'anomalie 
  * US4 – Consulter certaines valeurs spécifiques de l'historique des mesures -- dev
  * US5 – Préparer l'interface de paramétrage spécifique
  * US6 – Configurer les valeurs par défaut des modèles
 

* **Enzo DUPUIS**
  
  * WI1 — Création d'une Class permettant d'analyser les données des capteurs
  * WI2 — Module d'injection et de priorisation des données simulées
  * WI3 — Simuler des scénarios
  * WI6 — Affichage du page pour le déclenchement des scénarios
  * WI7 — Création d'objets reliés a une ou plusieurs automatisations
  * WI8 — Mise a jour de l'états de l'objet en fonction de son automatisation
  * WI9 — Affichage de la liste des objets reliés aux automatisations
  * WI7 — Supprimer une automatisation
  * WI8 — Gestion des profils de simulation (Presets)
  * WI9 — Utiliser un modèle d’automatisation
  * WI10 — Parcourir les modèles disponibles
  * WI11 — Personnaliser un modèle d’automatisation

 
* **Tom DA ROCHA**
  * S1 - Rechercher des bibliothèques pour dessiner le plan 2D et gérer l'interactivité
  * P1 - Création d'un premier jet de l'interface d'une maison
  * US1 - En tant qu'utilisateur, je veux voir le tracé des murs et les délimitations des pièces, afin de reconnaître ma maison
  * US2 - En tant qu'utilisateur, je veux voir le nom de chaque pièce superposé sur le plan, afin d'identifier clairement les zones
  * US3 - En tant qu'utilisateur, je veux voir un message d'information clair si aucune pièce n'a encore été configurée, afin de ne pas penser que l'application a crash
  * US4 - En tant qu'utilisateur, je veux pouvoir voir l'état actuel d'un objet via une icone d'une pièce afin de savoir s'il est ON ou OFF
  * US5 - En tant qu'utilisateur, je veux que l'état des pièces soient mise à jour en temps réel afin d'être m'assurer que les informations que je vois soient vraies
  * US6 - En tant qu'utilisateur, je veux qu'un panneau latéral s'ouvre au clic sur une pièce, afin de préparer l'affichage des éléments sans quitter totalement la vue carte
  * US7 - En tant qu'utilisateur, je veux voir la liste des noms des objets présents dans la pièce sélectionnée, afin de savoir ce qui y est installé
  * US8 - En tant qu'utilisateur, je veux voir l'état actuel (ex: "Allumé", "Fermé") à côté de chaque nom dans la liste, afin de vérifier le statut individuel de chaque objet
  * US9 - En tant qu'utilisateur, je veux consulter l'intensité de fonctionnement d'un objet, afin de me rendre compte de son utilisation
  * US10 - En tant qu'utilisateur, je veux voir la dernière mesure d'un capteur afin de savoir si je dois modifier sa sensibilité
