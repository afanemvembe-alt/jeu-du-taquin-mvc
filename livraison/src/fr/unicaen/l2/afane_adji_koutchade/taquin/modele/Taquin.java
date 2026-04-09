package fr.unicaen.l2.afane_adji_koutchade.taquin.modele;

import java.util.Random;
import java.util.Stack;
import fr.unicaen.l2.afane_adji_koutchade.taquin.util.AbstractTaquinEcoutable;

/**
 * Modèle du jeu de Taquin (Logique pure).
 * Ce fichier gere la grille et les déplacements sans connaître l'interface graphique.
 */
public class Taquin extends AbstractTaquinEcoutable {

    /** Nombre de lignes de la grille */
    private int ligne;
    /** Nombre de colonnes de la grille */
    private int colonne;
    /** Matrice representant l'état actuel du plateau */
    private int[][] tableau;

    /** Pile stockant les états précédents pour la fonctionnalité de retour */
    private Stack<int[][]> historique = new Stack<>();
    /** Compteur du nombre de coups joués */
    private int nbCoups = 0;
    /** Nombre d'indices encore disponibles pour le joueur */
    private int indicesRestants = 3;

    /** Coordonnée ligne de la case vide */
    private int videL;
    /** Coordonnée colonne de la case vide */
    private int videC;

    /**
     * Constructeur du jeu. Initialise la grille dans l'ordre croissant.
     * @param line nombre de lignes
     * @param row nombre de colonnes
     */
    public Taquin(int line, int row) {
        this.ligne = line;
        this.colonne = row;

        tableau = new int[line][row];
        int valeur = 1;

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                tableau[i][j] = valeur++;
            }
        }

        tableau[line - 1][row - 1] = 0;
        videL = line - 1;
        videC = row - 1;
    }

    // --- ACCESSEURS ---
    
    /**
     * Retourne le nombre de coups effectués depuis le début ou le dernier mélange.
     * @return le nombre de coups effectués
     */
    public int getNbCoups() { return nbCoups; }

    /**
     * Retourne le nombre d'indices utilisables restants.
     * @return le nombre d'indices restants
     */
    public int getIndicesRestants() { return indicesRestants; }

    /**
     * Retourne le nombre total de lignes.
     * @return le nombre de lignes
     */
    public int getLigne() { return ligne; }

    /**
     * Retourne le nombre total de colonnes.
     * @return le nombre de colonnes
     */
    public int getColonne() { return colonne; }

    /**
     * Retourne la valeur située à une position précise.
     * @param l ligne de la case
     * @param c colonne de la case
     * @return la valeur contenue dans la case
     */
    public int getValeur(int l, int c) { return tableau[l][c]; }

    // --- LOGIQUE DE JEU ---

    /**
     * Vérifie si un mouvement vers la case (l, c) est possible (doit être adjacente au vide).
     * @param l ligne cible
     * @param c colonne cible
     * @return vrai si le mouvement est autorisé
     */
    public boolean estPossible(int l, int c) {
        return l >= 0 && l < ligne && c >= 0 && c < colonne &&
                ((Math.abs(l - videL) == 1 && c == videC) ||
                 (Math.abs(c - videC) == 1 && l == videL));
    }

    /**
     * Effectue l'échange entre le vide et la case cible si le mouvement est possible.
     * @param l ligne cible
     * @param c colonne cible
     */
    public void echange(int l, int c) {
        if (estPossible(l, c)) {
            historique.push(copieTableau());
            tableau[videL][videC] = tableau[l][c];
            tableau[l][c] = 0;
            videL = l;
            videC = c;
            nbCoups++;
            // On previent les ecouteurs (Vue) que le modèle a changé
            this.taquinChangement(); 
        }
    }

    /**
     * Crée une copie profonde de la grille actuelle.
     * @return une matrice d'entiers identique au tableau actuel
     */
    private int[][] copieTableau() {
        int[][] copie = new int[ligne][colonne];
        for (int i = 0; i < ligne; i++)
            for (int j = 0; j < colonne; j++)
                copie[i][j] = tableau[i][j];
        return copie;
    }

    /**
     * Annule le dernier coup joué en restaurant le dernier état de l'historique.
     */
    public void retour() {
        if (!historique.isEmpty() && nbCoups > 0) {
            tableau = historique.pop();

            // On recalcule la position du vide après le retour
            for (int i = 0; i < ligne; i++) {
                for (int j = 0; j < colonne; j++) {
                    if (tableau[i][j] == 0) {
                        videL = i;
                        videC = j;
                    }
                }
            }

            nbCoups--;
            this.taquinChangement(); 
        }
    }

    /**
     * Effectue un mouvement automatique valide pour aider le joueur.
     */
    public void utiliserIndice() {
        if (indicesRestants > 0) {
            if (estPossible(videL - 1, videC)) echange(videL - 1, videC);
            else if (estPossible(videL + 1, videC)) echange(videL + 1, videC);
            else if (estPossible(videL, videC - 1)) echange(videL, videC - 1);
            else if (estPossible(videL, videC + 1)) echange(videL, videC + 1);
            indicesRestants--;
            this.taquinChangement();
        }
    }

    /**
     * Vérifie si toutes les tuiles sont à leur place d'origine.
     * @return vrai si le puzzle est résolu
     */
    public boolean fini() {
        int valeur = 1;
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (i == ligne - 1 && j == colonne - 1) {
                    return tableau[i][j] == 0;
                }
                if (tableau[i][j] != valeur) return false;
                valeur++;
            }
        }
        return true;
    }

    // --- MOUVEMENTS ---
// --- MOUVEMENTS (Action sur la case vide) ---

    /** Déplace la case vide vers le HAUT (Ligne - 1) */
    public void monter() { 
        echange(videL - 1, videC); 
    }

    /** Déplace la case vide vers le BAS (Ligne + 1) */
    public void descendre() { 
        echange(videL + 1, videC); 
    }

    /** Déplace la case vide vers la GAUCHE (Colonne - 1) */
    public void gauche() { 
        echange(videL, videC - 1); 
    }

    /** Déplace la case vide vers la DROITE (Colonne + 1) */
    public void droite() { 
        echange(videL, videC + 1); 
    }



    /**
     * Mélange la grille en effectuant n mouvements aléatoires valides.
     * @param n nombre de mouvements à effectuer
     */
    public void melanger(int n) {
        Random rand = new Random();
        historique.clear(); // On vide l'historique pour ne pas pouvoir tricher
        for (int i = 0; i < n; i++) {
            int dir = rand.nextInt(4);
            if (dir == 0) monter();
            else if (dir == 1) descendre();
            else if (dir == 2) gauche();
            else droite();
        }
        nbCoups = 0;
        indicesRestants = 3;
        this.taquinChangement();
    }
    /**
     * Retourne une représentation textuelle de la grille.
     * @return chaîne de caractères représentant le plateau
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                res.append(tableau[i][j]).append("\t");
            }
            res.append("\n");
        }
        return res.toString();
    }
}