package taquin.vue;

import javax.swing.*;
import java.awt.*;
import taquin.modele.Taquin;

/**
 * Panel qui affiche le Taquin sous forme de boutons.
 * Chaque bouton représente une case et réagit aux clics.
 */
public class GrillePanel extends JPanel {

    private Taquin modele;
    private TuileButton[][] boutons;

    public GrillePanel(Taquin modele) {
        this.modele = modele;

        // Création du layout selon le nombre de lignes et colonnes
        setLayout(new GridLayout(modele.getLigne(), modele.getColonne(), 5, 5));
        setBackground(Color.DARK_GRAY);

        boutons = new TuileButton[modele.getLigne()][modele.getColonne()];

        // Création des boutons pour chaque case
        for (int i = 0; i < modele.getLigne(); i++) {
            for (int j = 0; j < modele.getColonne(); j++) {
                boutons[i][j] = new TuileButton(modele, i, j, this);
                add(boutons[i][j]);
            }
        }
    }

    /**
     * Mise à jour des textes et couleurs des boutons après chaque déplacement
     */
    public void rafraichir() {
        for (int i = 0; i < modele.getLigne(); i++) {
            for (int j = 0; j < modele.getColonne(); j++) {
                boutons[i][j].mettreAJour();
            }
        }
    }
}