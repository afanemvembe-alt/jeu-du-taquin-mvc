package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import java.awt.*;
import javax.swing.*;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;

/**
 * Panel affichant la grille de jeu composée de boutons.
 */
public class GrillePanel extends JPanel {
    /** Le tableau de boutons représentant les tuiles */
    private TuileButton[][] boutons;
    /** Référence vers le modèle pour lire l'état du jeu */
    private Taquin modele;
    /** Référence vers le contrôleur pour lier les boutons */
    private TaquinControleSwing controleur;

    /**
     * Constructeur de la grille.
     * @param modele le modèle du jeu
     * @param controleur le contrôleur Swing
     */
    public GrillePanel(Taquin modele, TaquinControleSwing controleur) {
        this.modele = modele;
        this.controleur = controleur;

        int n = modele.getLigne();
        int m = modele.getColonne();
        this.setLayout(new GridLayout(n, m, 5, 5));

        boutons = new TuileButton[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boutons[i][j] = new TuileButton(i, j, controleur);
                this.add(boutons[i][j]);
            }
        }
        rafraichir();
    }

    /**
     * Met à jour l'affichage des boutons selon l'état du modèle.
     */
    public void rafraichir() {
        for (int i = 0; i < modele.getLigne(); i++) {
            for (int j = 0; j < modele.getColonne(); j++) {
                int val = modele.getValeur(i, j);
                if (val == 0) {
                    boutons[i][j].setText("");
                    boutons[i][j].setVisible(false);
                } else {
                    boutons[i][j].setText(String.valueOf(val));
                    boutons[i][j].setVisible(true);
                    
                    // Colore en vert clair si la tuile est cliquable
                    if (modele.estPossible(i, j)) {
                        boutons[i][j].setBackground(new Color(144, 238, 144));
                    } else {
                        boutons[i][j].setBackground(null);
                    }
                }
            }
        }
    }
}