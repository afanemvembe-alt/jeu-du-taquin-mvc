package taquin.vue;

import java.awt.event.*;
import javax.swing.*;

/**
 * Bouton représentant une tuile du Taquin.
 * Ne touche jamais directement le modèle : signale simplement le clic au listener.
 */
public class TuileButton extends JButton implements ActionListener {

    private int ligne, colonne;
    private VueListener listener;

    public TuileButton(int l, int c, VueListener listener) {
        this.ligne = l;
        this.colonne = c;
        this.listener = listener;

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.clicTuile(ligne, colonne);
    }
}
