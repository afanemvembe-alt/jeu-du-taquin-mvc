package taquin.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import taquin.modele.Taquin;

public class TuileButton extends JButton implements ActionListener {

    private Taquin modele;
    private int ligne;
    private int colonne;

    public TuileButton(Taquin modele, int l, int c) {

        this.modele = modele;
        this.ligne = l;
        this.colonne = c;

        int valeur = modele.getValeur(l, c);

        if (valeur == 0) {
            setText("");
            setEnabled(false);
            setBackground(Color.LIGHT_GRAY);
        } else {
            setText(String.valueOf(valeur));
            setFont(new Font("Arial", Font.BOLD, 28));
            setBackground(Color.WHITE);
        }

        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (modele.estPossible(ligne, colonne)) {
            modele.echange(ligne, colonne);
        }

        System.out.println(modele);
    }
}