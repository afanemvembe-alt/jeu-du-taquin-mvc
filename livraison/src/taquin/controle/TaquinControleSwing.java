package taquin.controle;

import taquin.modele.*;
import taquin.vue.*;

/**
 * Contrôleur pour le jeu de Taquin avec interface graphique Swing.
 * Gère les clics sur les tuiles et met à jour la vue correspondante.
 */
public class TaquinControleSwing implements VueListener {

    /** Modèle Taquin manipulé par le contrôleur */
    private Taquin modele;

    /** Fenêtre Swing représentant la vue */
    private TaquinFrame frame;

    /**
     * Constructeur du contrôleur Swing.
     * Crée une instance de TaquinFrame et lie le contrôleur au modèle.
     * 
     * @param modele le modèle Taquin à contrôler
     */
    public TaquinControleSwing(Taquin modele) {
        this.modele = modele;
        this.frame = new TaquinFrame(modele, this);
    }

    /**
     * Méthode appelée lorsqu'une tuile est cliquée dans la vue.
     * Déplace la tuile si le mouvement est possible et rafraîchit la vue.
     * 
     * @param l ligne de la tuile cliquée
     * @param c colonne de la tuile cliquée
     */
    @Override
    public void clicTuile(int l, int c) {
        if (modele.estPossible(l, c)) {
            modele.echange(l, c);
            frame.rafraichir();
        }
    }
}
