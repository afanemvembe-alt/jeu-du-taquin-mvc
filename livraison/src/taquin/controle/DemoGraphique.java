package taquin.controle;

import taquin.modele.Taquin;
import taquin.vue.TaquinFrame;

public class DemoGraphique {

    public static void main(String[] args) {
        Taquin jeu = new Taquin(4, 4);
        jeu.melanger(100);

        new TaquinFrame(jeu); // lance la fenêtre graphique
    }
}

