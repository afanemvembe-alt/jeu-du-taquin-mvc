package taquin.controle;

import javax.swing.SwingUtilities;
import taquin.modele.*;
import taquin.vue.*;

public class DemoGraphique {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Taquin jeu = new Taquin(4, 4);
            jeu.melanger(100);

            new TaquinControleSwing(jeu);
        });
    }
}