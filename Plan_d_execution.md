# Que fait le logiciel concrètement ? Et dans quel ordre ? #

## Lancement du logiciel pour la toute première fois ##

La base de donnée n'existe pas, la liste des flux est donc vide.

  1. Démarrage du logiciel
  1. Check de l'existence de la BdD
  1. Création de la BdD vide et création des tables
  1. (pour la suite, idem que dans le cas général, mais avec une liste de flux vide)

## Lancement du logiciel avec une BdD ##

  1. Démarrage du logiciel
  1. Check de l'existence de la BdD
  1. Importation de la liste de flux complète, et de toutes leurs news associées (dans le FeedManager)
  1. Récupération des url de chacun de ces flux
  1. Récupération des fichiers RSS grâce à ces url (dans le FeedCatcher)
  1. Création d'une nouvelle liste de flux et de news à partir de ces fichiers (dans le Parser)
  1. Fusion de la nouvelle liste et de l'ancienne, en ajoutant à l'ancienne les news nouvelles et en **conservant l'état** des news déjà existantes (non lue, favoris)
  1. Suppression des news trop vieilles (plus de 15 jours, par exemple, ou plus de 100 news dans ce flux) de la liste des flux
  1. Effacement complet du contenu des tables de la BdD
  1. Réécriture de la nouvelle liste de flux dans la BdD