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
