package taquin.modele;

import java.util.Random;

public class Taquin {
    private int ligne;
    private int colonne;
    private int[][] tableau; // Tableau 2D stockant les chiffres du puzzle

    // Positions de la case vide (le 0) pour savoir quoi déplacer
    private int videL;
    private int videC;

    public Taquin(int line, int row) {
        this.ligne = line; // Initialisation du nombre de lignes
        this.colonne = row; // Initialisation du nombre de colonnes
        int[][] table = new int[line][row]; // Création temporaire du tableau
        int valeur = 1; // Première valeur à placer dans la grille
        for (int i = 0; i < table.length; i++) { // Parcours des lignes
            for (int j = 0; j < table[i].length; j++) { // Parcours des colonnes
                table[i][j] = valeur; // Attribution du chiffre à la case
                valeur++; // Passage au chiffre suivant
            }
        }
        table[line - 1][row - 1] = 0; // Placement du vide sur la derniere case
        this.tableau = table; 

        // Initialiser la position du vide (en bas à droite au départ)
        this.videL = line - 1; // Ligne de la case 0
        this.videC = row - 1; // Colonne de la case 0
    }

    // Vérifie si la case aux coordonnées (l, c) est adjacente au vide
    public boolean estPossible(int l, int c) {
        // Vérifie si (l,c) est dans la grille et à exactement 1 case de distance du vide (pas en diagonale)
        return l >= 0 && l < ligne && c >= 0 && c < colonne && 
               ((Math.abs(l - videL) == 1 && c == videC) || (Math.abs(c - videC) == 1 && l == videL));
    }

    // Effectue l'échange entre une case choisie et la case vide
    public void echange(int l, int c) {
        if (estPossible(l, c)) { // On vérifie d'abord si le mouvement est autorisé
            this.tableau[videL][videC] = this.tableau[l][c]; // La case vide prend la valeur de la case choisie
            this.tableau[l][c] = 0; // La case choisie devient la nouvelle case vide
            this.videL = l; // Mise à jour de la position du vide
            this.videC = c; // Mise à jour de la position du vide
        }
    }
    
    /*Une methode "fini" qui renvoie vrai si le taquin(puzzle) est en ordre
     * renvoie faux si le taquin(puzzle) est toujours en desordre
    */
   public boolean fini() {
        int[][] table = new int[ligne][colonne]; 
        int valeur = 1; 
        for (int i = 0; i < table.length; i++) { 
            for (int j = 0; j < table[i].length; j++){
                table[i][j] = valeur;
                valeur++;
            }
        }
        boolean estVrai= true;
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                if(table[i][j]==tableau[i][j]){
                    estVrai = estVrai && true;
                }
                estVrai = estVrai && false;
                
            }
        }
        return estVrai;
   }
   









    // Méthodes de déplacement (Z, Q, S, W)
    public void monter() { echange(videL + 1, videC); } // On fait monter la pièce située sous le vide
    public void descendre() { echange(videL - 1, videC); } // On fait descendre la pièce située au-dessus du vide
    public void gauche() { echange(videL, videC + 1); } // On fait glisser vers la gauche la pièce à droite du vide
    public void droite() { echange(videL, videC - 1); } // On fait glisser vers la droite la pièce à gauche du vide

    // Mélange le puzzle en faisant glisser les pièces aléatoirement
    public void melanger(int nbCoups) {
        Random rand = new Random(); // Générateur de nombres aléatoires
        for (int i = 0; i < nbCoups; i++) { // Boucle selon le nombre de coups souhaité
            int dir = rand.nextInt(4); // Choix d'une direction au hasard (0 à 3)
            if (dir == 0) monter(); // Tentative de monter
            else if (dir == 1) descendre(); // Tentative de descendre
            else if (dir == 2) gauche(); // Tentative d'aller à gauche
            else if (dir == 3) droite(); // Tentative d'aller à droite
        }
    }

    // Getters pour que la Vue puisse lire les données sans modifier le modèle
    public int getValeur(int l, int c) { return tableau[l][c]; } // Retourne le chiffre d'une case
    public int getLigne() { return ligne; } // Retourne le nombre de lignes
    public int getColonne() { return colonne; } // Retourne le nombre de colonnes

    // Représentation du tableau en chaîne de caractères pour la console
    public String toString() {
        String res = ""; // Initialisation de la chaîne vide
        for (int i = 0; i < this.tableau.length; i++) { // Boucle sur les lignes
            for (int j = 0; j < this.tableau[i].length; j++) { // Boucle sur les colonnes
                res += this.tableau[i][j] + "\t"; // Ajout de la valeur avec une tabulation pour l'alignement
            }
            res += "\n"; // Saut de ligne à la fin de chaque rangée
        }
        return res;
    }
}
