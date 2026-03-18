package taquin.controle;

import taquin.modele.*;
import taquin.vue.*;

public class DemoGraphique {

    public static void main(String[] args) {
        Taquin jeu = new Taquin(4, 4);
        jeu.melanger(100);

        new TaquinControleSwing(jeu); // lance la fenêtre graphique
    }
}

