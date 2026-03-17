package taquin.controle;

import javax.swing.*;
import java.awt.*;
import taquin.modele.*;
import taquin.vue.*;

/**
 * Fenêtre principale du jeu Taquin.
 */
public class TaquinControleSwing extends JFrame {

    private Taquin modele;
    private TaquinVueSwing grille;

    public TaquinControleSwing(Taquin modele) {
        this.modele = modele;

        setTitle("Jeu du Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grille = new TaquinVueSwing(modele);
        add(grille, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // centrer la fenêtre
        setVisible(true);
    }
}
