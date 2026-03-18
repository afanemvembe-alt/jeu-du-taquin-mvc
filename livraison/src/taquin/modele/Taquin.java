package taquin.modele;

import java.util.Random;

public class Taquin extends AbstractTaquinEcoutable{
    private int ligne;
    private int colonne;
    private int[][] tableau; 

    // Positions de la case vide pour savoir quoi déplacer
    private int videL;
    private int videC;

    public Taquin(int line, int row) {
        this.ligne = line;
        this.colonne = row; 
        int[][] table = new int[line][row];
        int valeur = 1; 
        for (int i = 0; i < table.length; i++) { 
            for (int j = 0; j < table[i].length; j++) { 
                table[i][j] = valeur; 
                valeur++; 
            }
        }
        table[line - 1][row - 1] = 0; // Placement du vide sur la derniere case
        this.tableau = table; 

        // Initialiser la position du vide 
        this.videL = line - 1; 
        this.videC = row - 1;
    }

    public boolean estPossible(int l, int c) {
        // Verification si (l,c) est dans la grille et à exactement 1 case de distance du vide
        return l >= 0 && l < ligne && c >= 0 && c < colonne && 
               ((Math.abs(l - videL) == 1 && c == videC) || (Math.abs(c - videC) == 1 && l == videL));
    }

    // Effectue l'échange entre une case et la case vide
    public void echange(int l, int c) {
        if (estPossible(l, c)) { // pour verifier si le mouvement est possible
            this.tableau[videL][videC] = this.tableau[l][c]; // La case vide prend la valeur de la case 
            this.tableau[l][c] = 0;  
            this.videL = l; 
            this.videC = c;
            this.taquinChangement();
        }
    }
    
    /*Une methode "fini" qui renvoie vrai si le taquin(puzzle) est en ordre
     * renvoie faux si le taquin(puzzle) est toujours en desordre
    */
    /* Une methode "fini" qui renvoie vrai si le taquin(puzzle) est en ordre
	 * renvoie faux si le taquin(puzzle) est toujours en desordre
	*/
	public boolean fini() {
		int valeur= 1;
		for (int i = 0; i < ligne; i++) { 
			for (int j = 0; j < colonne; j++) {
				
				// Si on est sur la derniere case
				if (i == ligne - 1 && j == colonne - 1) {
					// contient 0 pour que ce soit fini
					return this.tableau[i][j] == 0;
				}
				
				// Pour les autres on compare la valeur actuelle avec ce qu'on attend
				if (this.tableau[i][j] != valeur) {
					return false;
				}
				
				valeur++;
			}
		}
		return true; 
	}



    // Méthodes de déplacement
    public void monter() { echange(videL + 1, videC); } // On fait monter la pièce qui est sous le vide
    public void descendre() { echange(videL - 1, videC); } // On fait descendre la pièce située au-dessus du vide
    public void gauche() { echange(videL, videC + 1); } // On fait glisser vers la gauche la pièce à droite du vide
    public void droite() { echange(videL, videC - 1); } // vers la droite à gauche du vide

    // Mélange du puzzle en faisant glisser les pieces aleatoirement
    public void melanger(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) { 
            int dir = rand.nextInt(4); // Choix de direction au hasard
            if (dir == 0) monter(); // monter
            else if (dir == 1) descendre();
            else if (dir == 2) gauche(); // aller à gauche
            else if (dir == 3) droite(); 
        }
        this.taquinChangement();
    }

    // Getters pour que la Vue puisse lire les données sans modifier le modele
    public int getValeur(int l, int c) { return tableau[l][c]; } 
    public int getLigne() { return ligne; } 
    public int getColonne() { return colonne; } 

    // Representation du tableau en chaine de caracteres
    public String toString() {
        String res = ""; 
        for (int i = 0; i < this.tableau.length; i++) { 
            for (int j = 0; j < this.tableau[i].length; j++) { 
                res += this.tableau[i][j] + "\t"; 
            }
            res += "\n";
        }
        return res;
    }
}
