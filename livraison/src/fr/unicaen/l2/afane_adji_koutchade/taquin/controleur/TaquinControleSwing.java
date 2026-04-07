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
    
/**
     * Gestion du clavier (Z, Q, S, D/W) conforme aux exigences :
     * Z : déplace l'élément du haut (le vide monte)
     * S : déplace l'élément du bas (le vide descend)
     * Q : déplace l'élément de gauche (le vide va à gauche)
     * D/W : déplace l'élément de droite (le vide va à droite)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        switch (code) {
            case KeyEvent.VK_Z: 
            case KeyEvent.VK_UP:    
                modele.monter(); // La pièce du haut descend dans le vide
                break;
                
            case KeyEvent.VK_S: 
            case KeyEvent.VK_DOWN:  
                modele.descendre(); // La pièce du bas monte dans le vide
                break;
                
            case KeyEvent.VK_Q: 
            case KeyEvent.VK_LEFT:  
                modele.gauche(); // La pièce de gauche glisse à droite
                break;
                
            case KeyEvent.VK_D: 
            case KeyEvent.VK_W: // Le sujet mentionne W comme alternative à D
            case KeyEvent.VK_RIGHT: 
                modele.droite(); // La pièce de droite glisse à gauche
                break;
        }
    }
}