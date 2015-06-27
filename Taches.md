# Nouvelle liste des tâches #

  * Finir la Javadoc
  * Vérifier la connexion internet : fonction gotInternetConnection
  * Séparer la synchronisation dans un Thread séparer pour que le logiciel ne freeze pas pendant la recherche des flux
  * Corriger le ContentPanel : certains caractères spéciaux ne passent pas et empêchent l'affichage de toute la news
  * Faire le rapport commun


---



---



---


# Liste des tâches #

  * ~~Finir les tests unitaires, commencer les tests unitaires du Model (je crois que David est dessus)~~

  * ~~Finir le Model (corriger la fonction sync() peut-être que le problème vient de obtainDocument)~~

  * ~~Intégrer la base de donnée avec le reste (faire fonctionner le projet avec des news se situant dans la base de donnée !!!)~~

  * ~~Donc si quelqu’un se sent chaud pour remplir une base de données avec des catégories, flux, news réelles contenant les bon urls de vrais sites~~

  * Tout le monde doit finir leur Javadoc de leurs classes.

Petit rappel : pour chaque fonction, _clic-droit > Source > Generate Element Comment_ ou bien le raccourci clavier _ALT+ Shift + J_ au-dessus de la fonction.

Pour rappel je vous avais envoyé un mail sur ce sujet :

> « Je vous ai mis un exemple de javadoc dans le drive.

> Si vous voulez plus d'info sur la javadoc vous pouvez regarder :

> http://fr.openclassrooms.com/informatique/cours/presentation-de-la-javadoc/les-tags-javadoc-1

> Et pour générer la javadoc :

> http://fr.openclassrooms.com/informatique/cours/presentation-de-la-javadoc/generer-la-javadoc-avec-eclipse-1 »

  * ~~Finir la fenêtre qui permettra de gérer les sources (l’utilisateur pourra ajouter ou supprimer un flux) puis ajouter les évènements dessus (Controller)~~

  * ~~Pour cela j’aimerais que quelqu’un fasse les methodes dans le model~~

> ~~addFeed(Feed feed, Caterory cat)~~

> ~~removeFeed(Feed feedm Category cat)~~

> ~~Il faudra ajouter/supprimer le feed dans OldListCategory du feedManager (et le fouttre dans la bonne catégory), puis faire un appel en fin de fonction à notifyObserver pour notifier la vue~~

  * ~~Ajouter la possibilité de changer le Style des news~~

  * Supprimer tous les merveilleux if dans le Controller

  * Implémenter des design patterns comme par exemple le design pattern state (pour les états lu, non, lu et favoris), si vous avez d’autres idées je suis preneur. Peux être le design pattern singleton.

> En effet il en faut au moins 5 pour avoir tous les points sur la partie design pattern du projet, c’est un peu con mais c’est comme ça.

  * Eventuellement améliorer le design de la vue

  * Eventuellement essayer de factoriser votre code, essayer de noter à quel endroit vous avait fait votre refactorisation (peut être utile pour le rapport personnel et les questions à l’oral)

  * Commencer le rapport commun, c’est peut-être une bonne idée de le faire sous Latex, certains d’entre nous connaisse ce langage.



---



---



---


# Ancienne liste des tâches à effectuer #

## Mise en place de la Base de Donnée ##

  * SQL et MERISE
  * SGBD (SQLite)
  * La classe DBManager
  * Tests unitaires avec JUnit

-> Vincent et Matthieu

## Interface graphique ##

-> Yann, Rémi, Bastien et Julien

## FeedCatcher et Parser ##

  * Récupérer les flux RSS sur internet
  * Transformer en classe

-> Julien, Vincent et Matthieu

## Feed, News et Categorie ##

  * UML classe
  * Méthodes

-> Benjamin, David et Julien

## FeedManager & Model ##

-> Bastien, Gauthier, Yann et Rémi

# Controller #

  * Faire ça à la fin