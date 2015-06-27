# Configurer son PC pour développer le projet #

## Pré-requis ##

  * Eclipse Juno (3.7) ou supérieur
  * Mac, Windows, Linux, peut importe

## Installer Subclipse ##

  * Allez sur http://subclipse.tigris.org/, cliquez sur _Download & Install_.

  * Dans la section _Links for 1.10.x Release_, copiez le lien nommé _Eclipse update site URL_.

  * Dans Eclipse, allez dans le menu _Help_ > _Install New Softwares_.

  * Cliquez sur _Add_. Dans _Location_, collez le lien.

  * Cliquez sur _Select all_ et sur _Finish_.

  * Redémarrez Eclipse.

## Configurer Subclipse ##

  * Cliquez sur _Windows_ > _Open perspective_ > _Other..._ et choisissez _SVN Repository Exploring_.

  * Dans le nouveau panneau de gauche, vide pour l'instant, faites _Clic droit_ > _New_ > _Repository Location_.

  * Copiez-collez `https://actu-rss.googlecode.com/svn`

  * Allez dans le menu _File_ > _New_ > _Project..._ (et pas _Java Project_). Choisissez _SVN_ > _Checkout Project from SVN_.

  * Choisissez `https://actu-rss.googlecode.com/svn` dans la liste, cliquez sur _Next_, puis sélectionnez _Actu-RSS_. Cliquez sur _Finish_.


## Le premier commit ##

Un identifiant et un mot de passe vous seront demandés.
L'identifiant est votre adresse GMail, le mot de passe est disponible [ici](https://code.google.com/hosting/settings), en haut et en gras.