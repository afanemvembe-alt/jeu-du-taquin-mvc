package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import javax.swing.*;
import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;

/**
 * Bouton personnalisé représentant une tuile de la grille du Taquin.
 */
public class TuileButton extends JButton {
    /** La ligne de la tuile dans la grille */
    private int ligne;
    /** La colonne de la tuile dans la grille */
    private int colonne;

    /**
     * Constructeur d'un bouton tuile.
     * @param l index de la ligne
     * @param c index de la colonne
     * @param controleur référence vers le controleur pour gérer le clic
     */
    public TuileButton(int l, int c, TaquinControleSwing controleur) {
        this.ligne = l;
        this.colonne = c;
        
        // On lie directement le clic au contrôleur
        this.addActionListener(e -> controleur.clicSurTuile(ligne, colonne));
    }
}