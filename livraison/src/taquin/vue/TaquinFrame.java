package taquin.vue;

import javax.swing.*;
import java.awt.*;
import taquin.modele.Taquin;

/**
 * Fenêtre principale du jeu Taquin.
 */
public class TaquinFrame extends JFrame {

    private Taquin modele;
    private GrillePanel grille;

    public TaquinFrame(Taquin modele) {
        this.modele = modele;

        setTitle("Jeu du Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grille = new GrillePanel(modele);
        add(grille, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // centrer la fenêtre
        setVisible(true);
    }
}