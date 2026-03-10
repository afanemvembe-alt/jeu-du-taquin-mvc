L'architecture actuelle du projet:

livraison
    │   Readme.md
	|
    ├───diagrammes
    │   │   TaquinDiagramme.drawio
    │   │
    │   └───vue
    │           DiagrammeDeClasseDeLaVue (3).drawio
    │           DiagrammeSequenceCliqueSurTuile.drawio
    │
    ├───doc
    │       planningVue.md
    │
    ├───rapport
    │       rapport.pdf
    │
    └───src
        └───taquin
            ├───controle
            │       Demo.java
            │       TaquinControleConsole.java
            │
            ├───modele
            │       Demo.java
            │       Demo2.java
            │       Taquin.java
            │
            └───vue
                    GrillePanel.java
                    TaquinFrame.java,
                    TuileButton.java

Quelques précisions sur la vue console si besoin :
	*La vue console doit prendre uniquement une instance de Taquin
	*C’est la vue qui affiche la grille et les messages dans le terminal
	*Le contrôleur (TaquinControleConsole) reste celui qui lit les entrées 
	 utilisateur et appelle la vue pour l’affichage. 
	*Ne pas utiliser le toString() de Taquin pour afficher la grille(apparemment)
	
	
	
	
	
	
	
	




	
	
	Comment fonctionne le clic sur une tuile (Pour mieux comprendre et avancer )

L’utilisateur clique sur un bouton (TuileButton) correspondant à une tuile.

Le bouton reçoit l’événement de clic grâce à actionPerformed().

Le bouton demande au modèle si le déplacement est possible via Taquin.estPossible(ligne, colonne).

Si le déplacement est possible, le modèle échange la tuile avec la case vide via Taquin.echange(ligne, colonne).

L’état du modèle est ainsi mis à jour.

(Prochaine étape) La vue se rafraîchira pour afficher les tuiles déplacées dans la grille graphique.

Remarque : pour l’instant, le déplacement est visible dans la console via System.out.println(). La mise à jour graphique sera implémentée pour un affichage complet.



MVC : le modèle gère la logique, la vue gère l’affichage, le contrôleur gère les clics.

TuileButton est le lien entre la Vue et le Modèle : il reçoit le clic et appelle le modèle.

Taquin ne connaît pas la vue : il se contente de gérer l’état du puzzle.



# Compilation
javac -d build src/taquin/modele/*.java src/taquin/vue/*.java src/taquin/controle/*.java

# Exécution du Demo graphique
java -cp build taquin.controle.DemoGraphique
