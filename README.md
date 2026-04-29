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
  * WI10 - Création d’un mock de contrôle des objets domotiques 
  * WI11 - Création d’un mock de contrôle des objets domotiques 
  * WI12 - Création d’un mock de contrôle des objets domotiques 
  * WI13 — Création d’un mock de contrôle des objets domotiques 
  * WI14 — Création d’un mock de contrôle des objets domotiques 

 
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
 

  # Documentation

Topologie Réseau (Architecture)

L'accès à notre infrastructure est sécurisé et nécessite d'être connecté au réseau de l'école (via le VPN EPISEN).
Au cœur de cette architecture se trouve un pare-feu OPNsense, qui agit comme une passerelle de sécurité entre le réseau global de l'école (WAN) et notre sous-réseau privé isolé (LAN Domotique en 192.168.1.0/24).

Ce réseau privé héberge plusieurs machines virtuelles dédiées à des rôles spécifiques :

Environnement de Production : Séparé en trois instances distinctes pour le frontend (Prod FrontEnd), le backend (Prod API) et la base de données (Prod DB).

Environnement d'Intégration : Une machine dédiée (Integration) regroupant le front et l'API pour valider les développements avant la mise en production.

CI/CD : Deux Runners GitHub Actions chargés d'exécuter nos pipelines de déploiement continu de manière autonome au sein du réseau privé.

 * Schéma de la topologie réseau

flowchart LR
    USER[Utilisateur] --> VPN
    
    VPN[VPN EPISEN<br>172.31.240.0/21] --> WAN[LAN EPISEN<br>172.31.248.0/21]

    WAN <--> OPNsense[OPNsense<br>172.31.254.38<br>192.168.1.1]

    subgraph LAN[LAN Domotique 192.168.1.0/24]
        API[Prod API<br>192.168.1.2]
        DB[Prod DB<br>192.168.1.3]
        FE[Prod FrontEnd<br>192.168.1.4]
        INT[Integration<br>192.168.1.5]
        RUN1[Github Actions Runner 1<br>192.168.1.6]
        RUN2[Github Actions Runner 2<br>192.168.1.7]
    end

    OPNsense <--> API
    OPNsense <--> DB
    OPNsense <--> FE
    OPNsense <--> INT
    OPNsense <--> RUN1
    OPNsense <--> RUN2

Routage et Reverse proxy (Nginx)

Pour simplifier l'accès aux différents services sans exposer directement les ports de nos machines internes, nous utilisons Nginx (configuré sur le routeur OPNsense) comme reverse proxy. Il se charge de rediriger le trafic entrant vers la bonne machine en fonction de l'URL demandée :

Pour les Utilisateurs finaux :

L'URL principale (domotique.inside.esipe-creteil.info) pointe vers l'interface utilisateur en production.

Le chemin /api redirige de manière transparente vers le backend de production.

Pour les Développeurs :

Le sous-domaine inte.* permet d'accéder à l'environnement de test (Frontend et API d'intégration).

Le sous-domaine opnsense.* permet d'accéder à l'interface d'administration de notre pare-feu/routeur en toute sécurité.

 * Schéma de routage et du reverse proxy

flowchart LR
    USER[Utilisateur] --> URL1
    USER --> URL2
    DEV[Développeurs] --> URL3
    DEV --> URL4
    DEV --> URL5

    subgraph OPNsense
      subgraph Nginx[Nginx<br>172.31.254.38:80]
          URL1[domotique.inside.esipe-creteil.info]
          URL2[domotique.inside.esipe-creteil.info/api]
          URL3[inte.domotique.inside.esipe-creteil.info]
          URL4[inte.domotique.inside.esipe-creteil.info/api]
          URL5[opnsense.domotique.inside.esipe-creteil.info]
      end
    end

    URL1 -- 192.168.1.4:80 --> FE[Prod FrontEnd]
    URL2 -- 192.168.1.2:8080 --> API[Prod API]

    URL3 -- 192.168.1.5:80 --> INT[Integration]
    URL4 -- 192.168.1.5:8080 --> INT

    URL5 -- 192.168.1.1:8000 --> OPN[OPNsense]


