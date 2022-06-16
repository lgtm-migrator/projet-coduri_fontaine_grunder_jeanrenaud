# Projet de Méthodologie DIL


Ce dépôt a été crée dans le context du cours DIL durant le 4 ème semestre de Bachelor en Technologies de l'information et de la communication à l'HEIG-VD.

## Qu'est-ce que DIL ?

DIL signifie Processus de développement en ingénierie logicielle dispensé par Bertil Chapuis. Ce cours à pour objectif d'enseigner les processus de développement en ingénierie logicielle.

## Objectif

Ce laboratoire à pour objectif d'entrainer la méthodologie à appliquer dans une équipe de développement.

### Détail

-   Un membre du groupe doit accepter l'assignment Github Classroom et créer le groupe du projet, les autres doivent le rejoindre.
-   Cloner le projet en local (avec ssh).
-   Configurer Github pour protéger la branche main et forcer la revue de code.
-   Créer un Kanban (Github projects).
-   Créer des issues pour chacune des tâches suivante sur votre repository Github :
    -   Créer et commencer la rédaction d'un README.
    -   Ajouter une license.
    -   Ajouter un code of conduct.
    -   Créer un projet Maven.
    -   Ajouter la dépendance Picocli à votre projet Maven.
    -   Créer une classe Main pour Picocli.
    -   Créer 4 sous-commandes (sans implémentation) avec Picocli :
        -   new
        -   clean
        -   build
        -   serve
-   Assigner les issues aux membres de votre groupe.
-   Créer une branche par issue.
-   Résoudre chaque issue dans sa branche respective.
-   Utiliser le Kanban pour suivre l'avancement de vos issues.
-   Créer des pull requests lorsque votre issue est résolue.
    -   Un de vos collègue devra faire une review votre PR (pull request) et faire des remarques constructives au besoin.
    -   Si la PR a été acceptée par votre collègue, vous devrez la merger dans la branche main.

## Manuel Utilisateur
### Introduction
Cette section est desinée au utilisateur aux utilisateurs de notre application. L'application permet de générer un site web rapidement en écrivant le contenu de ce dernier au format .md

### Prérequis
* Un ordinateur.
* Java 8 installé ou une version plus récente.

### Installation
* Télécharger le fichier jar [d'un release](https://github.com/dil-classroom/projet-coduri_fontaine_grunder_jeanrenaud/releases)

### Utilisation
* Pour utiliser l'application il faut executer le fichier jar dans un terminal :
```console
foo@bar:~$ java -jar static.jar
```
* Pour lancer une commande spécifique, executez le fichier jar en fournissant le nom de la commande à executer en paramètre :
```console
foo@bar:~$ java -jar static.jar version
```

#### Commandes
Ce chapitre a pour but d'expliquer l'utilité des différentes commandes disponible et comment les utiliser.

##### Version
Affiche la version de l'application.
##### Init
Paramètres :
* Dossier dans lequel le dossier init sera crée.

Initialise un dossier init à la position spécifiée en paramètre. Ce dossier contient tous les fichiers d'initialisation.

Les fichiers sont les suivants :
* une page d'exemple au format markdown
* un fichier de configuration au format YAML
##### Build
Paramètres :
* Position du dossier de configuration init

Crée un dossier build à la même position que le dossier de configuration fourni en paramètre. Ce dossier comporte les sources du site generées à partir des fichiers de configurations et du contenu dans le dossier de configuration.
La commande requiert un dossier template avec au minimum un fichier template.html à la racine du .jar

Le site géneré affiche le contenu à partir de l'arborescence de contenu, naviguable à l'aide d'un menu.
##### Clean
Paramètres :
* Chemin vers le dosser à nettoyer

Nettoie le dossier spécifier en supprimant le dossier build.
##### Serve
Paramètres :
* Chemin vers le dossier où déployer le site

Déploie le site sur un serveur HTTP à l'adresse localhost au port 8080.
## [License Apache 2.0](https://choosealicense.com/licenses/apache-2.0/)

## [Code de conduite](CODE_OF_CONDUCT.md)

## [Porfolio](https://docs.google.com/document/d/1ma4DtP58aYvNaCOSFSOzSmjbmZ5aXtBLXJ-PQsJb1zY/edit?usp=sharing)


# Authors

-   Coduri Luca
-   Fontaine Chloé
-   Grunder Alice
-   Jeanrenaud Nelson
