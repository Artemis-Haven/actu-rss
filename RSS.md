# RSS (Really Simple Syndication) #

Les flux RSS sont des fichiers XML qui sont souvent utilisés par les sites d'actualité et les blogs pour présenter les titres des dernières informations consultables en ligne.

La lecture d'un flux RSS se fait à l'aide d'un logiciel agrégateur. Il faut lui amener l'adresse du fil RSS désiré (par exemple par copier/coller) afin que l'agrégateur se connecte au site émetteur afin de vérifier la présence de nouveau contenu. Si c'est le cas, le flux RSS est téléchargé et converti immédiatement au format HTML pour en permettre la lecture.

Le principal avantage de cette technologie est de permettre de suivre facilement un flux d'informations (par exemple les nouveaux articles d'un site Web d'actualités) sans avoir la nécessité de s'y rendre manuellement à l'aide un navigateur web. Ainsi on s'épargne le temps de lancement du logiciel navigateur web, et le temps nécessaire au téléchargement et à l'affichage des images de la page Web d'origine.

Il existe différents types d'agrégateurs (tresseurs) : les lecteurs en ligne (au moyen d'un site web), les logiciels spécifiques locaux, certains clients de messagerie et certains navigateurs web. Pour une liste de noms, voir la page agrégateur et la Catégorie:Agrégateur.

La plupart des fils d'actualités RSS sont accessibles librement mais certains peuvent être soumis à un abonnement qui doit être souscrit sur le site du flux en question.
> _Wikipedia_

# Métadonnées #

Un flux contient des métadonnées obligatoires :
  * `<title>`
  * `<description>`
  * `<link>`
Et d'autres facultatives :
  * `<pubDate>`
  * `<lastBuildDate>`
  * `<image>`
  * `<enclosure>`
  * `<language>`

Idem pour les news, métadonnées obligatoires :
  * `<title>`
  * `<description>`
  * `<pubDate>`
  * `<link>`
  * `<guid>`
Et facultatives :
  * `<author>`
  * `<category>`
  * `<comments>`


# Détails #

Un fichier RSS ressemble à ça :

```
<?xml version="1.0" encoding="iso-8859-1"?>
<rss version="2.0">
    <channel>
        <title>Mon site</title>
        <description>Ceci est un exemple de flux RSS 2.0</description>
        <lastBuildDate>Sat, 07 Sep 2012 16:12:42 GMT</lastBuildDate>
        <link>http://www.example.org</link>
        <item>
            <title>Actualité N°3</title>
            <description>Ceci est ma 3e actualité</description>
            <pubDate>Sat, 07 Sep 2012 00:02:01 GMT</pubDate>
            <link>http://www.example.org/actu3</link>
        </item>
        <item>
            <title>Actualité N°2</title>
            <description>Ceci est ma 2e actualité</description>
            <pubDate>Sat, 07 Sep 2012 00:01:01 GMT</pubDate>
            <link>http://www.example.org/actu2</link>
        </item>
        <item>
            <title>Actualité N°1</title>
            <description>Ceci est ma première actualité</description>
            <pubDate>Sat, 07 Sep 2012 00:00:01 GMT</pubDate>
            <link>http://www.example.org/actu1</link>
        </item>
    </channel>
</rss>
```