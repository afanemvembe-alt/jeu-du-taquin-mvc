package taquin.vue;

import javax.swing.*;
import java.awt.*;
import taquin.modele.Taquin;

public class TaquinFrame extends JFrame {

    private Taquin modele;
    private GrillePanel grille;

    public TaquinFrame(Taquin modele) {

        this.modele = modele;

        setTitle("Jeu du Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        grille = new GrillePanel(modele);
        add(grille, BorderLayout.CENTER);

        setSize(600,600); // fenêtre plus grande
        setLocationRelativeTo(null); // centre la fenêtre
        setVisible(true);
    }
}