package taquin.vue;

import java.awt.*;
import javax.swing.*;
import taquin.modele.*;

/**
 * Panel affichant la grille du Taquin.
 * Contient un bouton par case et permet de rafraîchir l'affichage.
 */
public class GrillePanel extends JPanel {

    private TuileButton[][] boutons;
    private Taquin modele;  // lecture seule
    private VueListener listener;

    public GrillePanel(Taquin modele, VueListener listener) {
        this.modele = modele;
        this.listener = listener;

        int lignes = modele.getLigne();
        int colonnes = modele.getColonne();
        setLayout(new GridLayout(lignes, colonnes));

        boutons = new TuileButton[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                boutons[i][j] = new TuileButton(i, j, listener);
                add(boutons[i][j]);
            }
        }

        rafraichir();
    }

    /**
     * Met à jour l'affichage en fonction de l'état du modèle.
     */
    public void rafraichir() {
        for (int i = 0; i < modele.getLigne(); i++) {
            for (int j = 0; j < modele.getColonne(); j++) {
                int val = modele.getValeur(i, j);
                boutons[i][j].setText(val == 0 ? "" : String.valueOf(val));
                boutons[i][j].setEnabled(val != 0 && modele.estPossible(i, j));
            }
        }
    }
}
