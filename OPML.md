# OPML (Outline Processor Markup Language) #

OPML est un format XML permettant de regrouper et d'identifier les titres (outlines) d'un texte. Il a été adopté pour de multiples usages, le plus commun d'entre eux étant l'échange de listes de flux RSS entre les agrégateurs de flux de syndication.
> _Wikipedia_

En gros, un fichier OPML est un fichier qui contient une liste de flux RSS, et qui permet d'exporter/d'importer une liste vers ou depuis un logiciel d'agrégation de flux RSS comme le nôtre.

# Détails #

Un fichier OPML ressemble à ça :

```
<opml version="1.1">

   <head>
      <title>Export de la liste de flux RSS</title>
      <dateCreated>Wed, 30 Oct 2013 03:56:00 +0100</dateCreated>
   </head>

   <body>
      <outline text="Main Folder">
         <outline title="MacGeneration" text="MacGeneration" type="rss" xmlUrl="http://megaflux.macg.co"/>
         <outline title="Le Gorafi" text="Le Gorafi" type="rss" xmlUrl="http://www.legorafi.fr/feed/"/>
         <outline title="iGen.fr" text="iGen.fr" type="rss" xmlUrl="http://feed.igen.fr/igeneration"/>
      </outline>
      <outline text="Sport">
         <outline title="L'Équipe" text="L'Équipe" type="rss" xmlUrl="www.lequipe.fr/rss/‎"/>
         <outline title="Sport24" text="Sport24" type="rss" xmlUrl="http://www.sport24.com/rssfeeds/sport24-accueil.xml"/>
      </outline>
      <outline text="Tech">
         <outline title="01Net" text="01Net" type="rss" xmlUrl="http://www.01net.com/rss/actus.xml"/>
      </outline>
      <outline text="Politique">
         <outline title="LeMonde.fr" text="LeMonde.fr" type="rss" xmlUrl="http://www.lemonde.fr/rss/une.xml"/>
      </outline>
   </body>

</opml>
```