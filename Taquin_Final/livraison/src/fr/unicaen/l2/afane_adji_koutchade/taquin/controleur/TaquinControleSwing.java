package fr.unicaen.l2.afane_adji_koutchade.taquin.controleur;

import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.vue.TaquinFrame;
import java.awt.event.*;

/**
 * Le contrôleur fait le lien entre la Vue et le Modèle.
 * Il implémente KeyListener pour les touches du clavier.
 */
public class TaquinControleSwing extends KeyAdapter {
    private Taquin modele;
    private TaquinFrame vue;
/**
     * Constructeur du contrôleur Swing.
     * @param modele le modèle à piloter
     */
    public TaquinControleSwing(Taquin modele) {
        this.modele = modele;
        // On crée la vue et on lui donne le modèle
        this.vue = new TaquinFrame(modele, this);
        
        // IMPORTANT : On demande à la vue d'écouter les touches du clavier 
        // via ce contrôleur
        this.vue.addKeyListener(this);
        this.vue.setFocusable(true);
        this.vue.requestFocusInWindow();
    }

   /**
     * Méthode appelée lors d'un clic sur une tuile.
     * @param l ligne de la tuile
     * @param c colonne de la tuile
     */
    public void clicSurTuile(int l, int c) {
        if (modele.estPossible(l, c)) {
            modele.echange(l, c);
        }
    }

    /**
     * Gestion du clavier (Z, Q, S, W)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        char touche = Character.toLowerCase(e.getKeyChar());
        switch (touche) {
            case 'z': modele.monter(); break;
            case 's': modele.descendre(); break;
            case 'q': modele.gauche(); break;
            case 'w': modele.droite(); break;
        }
    }
}