# Norme de documentation des programmes #

## Introduction ##

Ce document décrit de manière très synthétique des règles concernant la documentation du code source des programmes C++ et Java. Ce document propose aussi des lignes directrices quand à la mise en forme du code source (indentation, espacement, etc).

## Documentation ##

### Principes généraux ###

  * La documentation s’adresse aux lecteurs et aux utilisateurs d’un programme, non pas à son auteur (sauf 6 mois après l’avoir écrit; l’auteur ne se souvient alors plus très bien de ce qu’il a fait).

  * Elle aide le lecteur à comprendre le code source et à l’utiliser; elle ne duplique pas les informations déjà contenues de manière explicite dans le code source.

  * Elle doit être claire, concise, précise, complète, uniforme et simple à maintenir.

  * Elle doit être faite au fur et à mesure que le programme est développé, sinon elle ne sera jamais faite (correctement).

  * Elle facilite la maintenance des programmes dans une organisation en uniformisant le style de programmation, ce qui permet à une personne de reprendre plus facilement le travail d’une autre.



### Fichier source : Chaque fichier source comprend une entête constituée des éléments suivants : ###

  * le nom du programme (ie, le nom du fichier source)

  * une courte description (quelques lignes)

  * les auteurs et leurs matricules;

  * la date de fin de première réalisation;

  * la date de fin de chaque version avec les auteurs et matricules;

  * le cours pour lequel le fichier est développé;

  * une description plus longue qui contient : les entrées du programme et leur préconditions, les sorties du programme et leur postconditions.

  * un historique des modifications pour chaque version.



### Méthode et fonction : Chaque méthode et chaque fonction : ###

  * comprend une entête décrivant le traitement effectué; idéalement, on décrit les préconditions sur les entrées et les postconditions sur les sorties;

  * comprend, au besoin, des commentaires dans le corps qui expliquent les sections plus complexes du traitement effectué;


&lt;wiki:gadget url="http://hosting.gmodules.com/ig/gadgets/file/108257444249077518195/google-drive.xml" height="200" border="0" /&gt;