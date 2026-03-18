package taquin.vue;

import javax.swing.*;
import taquin.modele.*;

/**
 * Fenêtre principale du Taquin.
 * Reçoit le modèle et le listener (contrôleur).
 */
public class TaquinFrame extends JFrame {

    private Taquin modele;
    private VueListener listener;
    private GrillePanel grille;

    public TaquinFrame(Taquin modele, VueListener listener) {
        this.modele = modele;
        this.listener = listener;

        setTitle("Jeu du Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grille = new GrillePanel(modele, listener);
        add(grille);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Méthode pour que le contrôleur puisse demander un rafraîchissement.
     */
    public void rafraichir() {
        grille.rafraichir();
    }
}
