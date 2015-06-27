# Résumé des séances #

## Réunion du mardi 29 oct. ##

  * Mise en place du Google Code
  * Mise en place de la liste de diffusion Google Groups
  * Installation et configuration d'Eclipse et du plugin pour SVN sur tous les postes de travail
  * Définition des rôles
  * Création du Wiki

### Pour la prochaine fois ###

  * Lister et distribuer les tâches


---


## Réunion du mercredi 30 oct. ##

  * Liste de toutes les tâches à effectuer
  * Distribution des tâches
  * Création des packages dans le projet
  * Création des branches dans SVN
  * Mise à jour du Wiki

### Pour la prochaine fois ###

Pour le mardi 5 nov.
  * Faire les classes News, Feed et Category
  * Faire la BdD et les tables avec SQLite
  * Définir complètement l'interface
  * Définir correctement le rôle des classes FeedManager et Model


---


## Réunion du mardi 5 nov. ##

  * Point sur l'avancée du travail
  * Définition des règles de Javadoc
  * Définition du plan d'exécution du logiciel

### Pour la prochaine fois ###

Pour le mardi 12 nov.
  * Finir la base de donnée : remplacer les requêtes de type SELECT par des PreparedStatement
  * Continuer l'interface
  * Angliciser les noms des variables et des méthodes
  * Commencer FeedCatcher et Parser


---


## Réunion du mardi 19 nov. ##

  * Les requetes dans DBManager utilisent des PreparedStatement
  * Toutes les variables sont en anglais
  * Début d'interface pour les listes de catégories et de flux à gauche, et de news au milieu
  * News, Feed et Category sont désormais dans le package commun
  * Création de la classe Model
  * Suppression de la classe FeedCatcher etintégration directement dans Model
  * ajout des fonctions clearDB et deleteDB dans DBManager
  * recherches pour le contentPane pour afficher un article en HTML

### Pour la prochaine fois ###

  * Continuer l'interface :
    * Panel d'affichage de la news : Rémi
    * ActuList : Gauthier
  * Commencer le contrôleur : Yann
  * Commencer et finir le Parser
  * Tester le modèle
  * Finir les tests unitaires de Feed, News et Category : Julian


---


## Réunion du mardi 26 nov. ##

  * Amélioration de l'interface :
    * Ajout d'un bouton "Lus"
    * Création du panneau des préférences
    * Création du panel d'affichage du contenu de l'actualité
    * Amélioration des actions entre les listes de Flux, de News, et les boutons de la Toolbar
  * Création du Parser
  * Correction de la fonction merge()
  * Continue de tester le Modèle et les classes Feed, News et Category
  * Contrôleur opérationnel
  * Création du fichier de config

### Pour la prochaine fois ###

  * Tester et corriger le ContentPanel pour l'affichage de la news
  * Finir tous les tests unitaires du Modèle et de Feed, News et Category
  * Intégrer le Parser, la Vue et la BdD ensembles
  * Tout regrouper dans une seule branche pour la validation, une fois que l'intégration des différents morceaux fonctionnera.
  * Finir le panneau des préférences


---


## Réunion du mardi 3 déc. ##

### Travail réalisé ###
  * Fusion du travail de tout le monde (branches vincentpoupi, view & benjidavid) dans le TRUNK.
  * Ajout d'un attribut Feed dans les News (modifications minimes dans le Parser et le DbManager).
  * Amélioration du ContentPanel, pas encore vraiment utilisable.

### Ordre du jour ###

  * Switch de tout le monde sur le trunk : https://actu-rss.googlecode.com/svn/trunk
  * Vérifier les tests unitaires après la fusion
  * Intégrer l'affichage de la news sélectionnée avec la BdD
  * Corriger la fonction merge() dans le FeedManager
  * Tester et vérifier la fonction sync() dans le Model
  * Commenter le code grâce à la Javadoc : pour chaque fonction, _clic-droit_ > _Source_ > _Generate Element Comment_. Bien remplir les @param, @throws et @return si nécessaire.
  * Le gros morceau : intégrer le Parser, le Model, la BdD et la Vue ensemble pour afficher un vrai flux RSS. -> **nécessite** de vérifier sync(), merge() et le ContentPanel.
  * Améliorer le rendu des news dans la liste de news.

### Pour la prochaine fois ###



---