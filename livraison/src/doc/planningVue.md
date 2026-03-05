# Planning à long terme – Partie Vue (MVC, projet Taquin)
**Durée : du 5 mars au 2 avril 2026**



## Semaine 1 (5 – 9 mars)
**Objectif général :** Comprendre la partie Vue et préparer la structure de base.
- **5 mars :**
  - Créer le dossier `vue` et fichiers principaux (`TaquinFrame.java`, `GrillePanel.java`, `TuileButton.java`).
  - Faire un premier diagramme de classes simple pour la Vue.
  - Commit initial sur la forge.
- **6 mars :**
  - Comprendre le flux MVC : utilisateur → Contrôleur → Modèle → Vue.
  - Faire un diagramme de séquence pour un clic sur une tuile.
- ** 7 mars :**
  - Définir les constructeurs et attributs de base pour `TaquinFrame` et `GrillePanel`.
  - Ajouter les méthodes vides avec commentaires (`rafraichir()`, `mettreAJourGrille()`).
- ** 8 mars :**
  - Créer la classe `TuileButton` avec attributs `ligne`, `colonne`, `modele`.
  - Ajouter les méthodes `mettreAJourAffichage()` et `setHighlightable(bool)`.
- **9 mars :**
  - Tester l’affichage initial des tuiles depuis le modèle.
  - Mettre à jour `planning.md` avec les remarques et difficultés rencontrées.
  - Commit sur la forge.


## Semaine 2 (12 – 16 mars)
**Objectif général :** Commencer l’interaction Vue-Modèle via le Contrôleur.
- **12 mars :**
  - Ajouter un bouton "Next" dans `TaquinFrame` pour tester la réaction de la Vue.
- **13 mars :**
  - Implémenter la méthode `rafraichir()` dans `GrillePanel` pour mettre à jour l’affichage des tuiles.
- ** 14 mars :**
  - Ajouter des écouteurs sur chaque `TuileButton` pour capter les clics.
  - Vérifier que la Vue change correctement selon les mouvements possibles.
- ** 15 mars :**
  - Tester les déplacements simulés depuis le modèle et voir la mise à jour automatique de la Vue.
- ** 16 mars :**
  - Commit sur la forge et mise à jour du diagramme de séquence si nécessaire.



## Semaine 3 (19 – 23 mars)
**Objectif général :** Finaliser l’affichage et interactions principales.
- ** 19 mars :**
  - Affichage du compteur de coups dans `TaquinFrame`.
- ** 20 mars :**
  - Test complet de la Vue en ligne de commande et graphique (rafraîchissement correct).
- ** 21 mars :**
  - Améliorer l’ergonomie : surbrillance des tuiles déplaçables (`setHighlightable(true)`).
- ** 22 mars :**
  - Ajouter messages ou feedback visuel pour l’utilisateur (ex : mouvement impossible).
- ** 23 mars :**
  - Commit et mise à jour du diagramme de classes dans `planning.md`.


## Semaine 4 (26 – 30 mars)
**Objectif général :** Stabiliser la Vue et préparer l’intégration complète.
- ** 26 mars :**
  - Test complet de la partie graphique avec différents modèles (`3x3`, `4x4`).
- ** 27 mars :**
  - Vérifier que tous les mouvements du modèle déclenchent bien la mise à jour de la Vue.
- ** 28 mars :**
  - Début intégration avec le Contrôleur final pour gestion des clics et touches clavier.
- ** 29 mars :**
  - Test des interactions simultanées clic + clavier.
- ** 30 mars :**
  - Commit final sur la forge et mise à jour du `planning.md` avec le résumé de la semaine.



## Semaine 5 (2 avril)
**Objectif général :** Préparer la livraison.
- ** 2 avril :**
  - Vérifier que toutes les vues (console et graphique) sont fonctionnelles.
  - Préparer les fichiers pour la livraison (`src`, `doc`, `dist`, `rapport`).
  - Commit final sur la forge.

