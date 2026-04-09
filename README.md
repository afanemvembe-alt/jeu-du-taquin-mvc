# Projet L2 - Jeu du Taquin (MVC)

Ce projet est une implémentation du jeu du Taquin en Java, réalisée dans le cadre de la licence informatique (L2). Il repose sur une architecture **MVC (Modèle-Vue-Contrôleur)** et utilise le **Pattern Observer** pour la communication entre le modèle et l'interface graphique.

## 👥 Équipe
* **Afane**
* **Adji**
* **Koutchade**

## ✨ Fonctionnalités
* **Mode Graphique (Swing)** : Une interface intuitive pour jouer à la souris.
* **Découpage d'Images (Extension)** : Le jeu découpe dynamiquement des fichiers JPG pour transformer le puzzle en photo.
* **Aide Visuelle (Extension)** : Coloration en vert des cases adjacentes au vide (déplaçables).
* **Contrôles Hybrides** : Support complet de la souris et du clavier (touches **Z, Q, S, D**).
* **Mode Console** : Une version textuelle est également disponible pour le debug.

## 📁 Structure du Projet
* `src/` : Code source Java organisé par packages.
* `bin/` : Fichiers compilés (.class).
* `livraison/dist/` : Contient l'exécutable `Taquin.jar`.
* `livraison/doc/` : Documentation technique (Javadoc) et Rapport final.
* `livraison/doc/rapport/` : Rapport de projet détaillant l'architecture MVC et UML.

## 🚀 Comment lancer le projet ?

### 1. Utiliser l'exécutable (Recommandé)
Le projet est livré avec un fichier JAR prêt à l'emploi. Depuis la racine du projet :
```bash
java -jar livraison/dist/Taquin.jar
