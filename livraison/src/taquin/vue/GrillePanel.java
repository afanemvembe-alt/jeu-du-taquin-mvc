package taquin.vue;

import javax.swing.*;
import java.awt.*;
import taquin.modele.Taquin;

public class GrillePanel extends JPanel {

    private Taquin modele;

    public GrillePanel(Taquin modele) {

        this.modele = modele;

        int lignes = modele.getLigne();
        int colonnes = modele.getColonne();

        setLayout(new GridLayout(lignes, colonnes));

        for(int i = 0; i < lignes; i++){
            for(int j = 0; j < colonnes; j++){

                JButton b = new JButton("" + modele.getValeur(i,j));
                add(b);

            }
        }
    }
}