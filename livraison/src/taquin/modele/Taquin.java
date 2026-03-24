package taquin.modele;

import java.util.Random;
import java.util.Stack;

/**
 * Modèle du jeu de Taquin.
 */
public class Taquin extends AbstractTaquinEcoutable {

    private int ligne;
    private int colonne;
    private int[][] tableau;

    private Stack<int[][]> historique = new Stack<>();
    private int nbCoups = 0;
    
    private int indicesRestants = 3;

    private int videL;
    private int videC;

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

    public int getNbCoups() {
        return nbCoups;
    }

    public int getIndicesRestants() {
        return indicesRestants;
    }

    public boolean estPossible(int l, int c) {
        return l >= 0 && l < ligne && c >= 0 && c < colonne &&
                ((Math.abs(l - videL) == 1 && c == videC) ||
                 (Math.abs(c - videC) == 1 && l == videL));
    }

    public void echange(int l, int c) {
        if (estPossible(l, c)) {

            historique.push(copieTableau());

            tableau[videL][videC] = tableau[l][c];
            tableau[l][c] = 0;

            videL = l;
            videC = c;

            nbCoups++;

            taquinChangement();
        }
    }

    private int[][] copieTableau() {
        int[][] copie = new int[ligne][colonne];
        for (int i = 0; i < ligne; i++)
            for (int j = 0; j < colonne; j++)
                copie[i][j] = tableau[i][j];
        return copie;
    }

    public void retour() {
        if (!historique.isEmpty()) {
            tableau = historique.pop();

            for (int i = 0; i < ligne; i++)
                for (int j = 0; j < colonne; j++)
                    if (tableau[i][j] == 0) {
                        videL = i;
                        videC = j;
                    }

            if (nbCoups > 0) nbCoups--;

            taquinChangement();
        }
    }

    public void utiliserIndice() {
    if (indicesRestants > 0) {

        if (estPossible(videL - 1, videC)) echange(videL - 1, videC);
        else if (estPossible(videL + 1, videC)) echange(videL + 1, videC);
        else if (estPossible(videL, videC - 1)) echange(videL, videC - 1);
        else if (estPossible(videL, videC + 1)) echange(videL, videC + 1);

        indicesRestants--;

        taquinChangement();
    }
}

    public boolean fini() {
        int valeur = 1;

        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {

                if (i == ligne - 1 && j == colonne - 1) {
                    return tableau[i][j] == 0;
                }

                if (tableau[i][j] != valeur) {
                    return false;
                }

                valeur++;
            }
        }
        return true;
    }

    public void monter() { echange(videL + 1, videC); }
    public void descendre() { echange(videL - 1, videC); }
    public void gauche() { echange(videL, videC + 1); }
    public void droite() { echange(videL, videC - 1); }

    public void melanger(int n) {
        Random rand = new Random();

        historique.clear();

        for (int i = 0; i < n; i++) {
            int dir = rand.nextInt(4);
            if (dir == 0) monter();
            else if (dir == 1) descendre();
            else if (dir == 2) gauche();
            else droite();
        }

        nbCoups = 0;
        indicesRestants = 3;

        taquinChangement();
    }

    public int getValeur(int l, int c) { return tableau[l][c]; }
    public int getLigne() { return ligne; }
    public int getColonne() { return colonne; }

    public String toString() {
        String res = "";
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                res += tableau[i][j] + "\t";
            }
            res += "\n";
        }
        return res;
    }
}

// les commentaires