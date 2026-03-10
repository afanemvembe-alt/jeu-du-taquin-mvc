package taquin.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import taquin.modele.Taquin;

/**
 * Bouton représentant une tuile du Taquin.
 * Lorsqu'on clique dessus, il déplace la tuile si c'est possible.
 */
public class TuileButton extends JButton implements ActionListener {

    private Taquin modele;
    private int ligne, colonne;
    private GrillePanel panel;

    public TuileButton(Taquin modele, int l, int c, GrillePanel panel) {
        this.modele = modele;
        this.ligne = l;
        this.colonne = c;
        this.panel = panel;

        setFont(new Font("Arial", Font.BOLD, 24));
        setPreferredSize(new Dimension(80, 80));
        setFocusPainted(false);
        setOpaque(true);

        mettreAJour(); // initialisation du texte et couleur
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (modele.estPossible(ligne, colonne)) {
            modele.echange(ligne, colonne);
            panel.rafraichir(); // mise à jour de toute la grille
        }
    }

    // Met à jour le texte et la couleur du bouton
    public void mettreAJour() {
        int valeur = modele.getValeur(ligne, colonne);
        setText(valeur == 0 ? "" : String.valueOf(valeur));
        setBackground(valeur == 0 ? Color.LIGHT_GRAY : Color.CYAN);
    }
}