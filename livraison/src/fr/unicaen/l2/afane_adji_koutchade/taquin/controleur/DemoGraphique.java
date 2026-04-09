package fr.unicaen.l2.afane_adji_koutchade.taquin.controleur;

import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import javax.swing.SwingUtilities;

/**
 * Point d'entrèe pour la version avec interface graphique (Swing).
 */
public class DemoGraphique {

    /** Constructeur par défaut. */
    public DemoGraphique() {}

    /**
     * Point d'entrée principal. Lance l'interface dans le thread Event Dispatch Thread (EDT).
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        // SwingUtilities.invokeLater assure que l'interface 
        // se lance dans le thread dédié à l'affichage (EDT).
        SwingUtilities.invokeLater(() -> {
            // 1. Création du Modèle
            Taquin jeu = new Taquin(4, 4);
            
            // 2. Melange
            jeu.melanger(100);

            // 3. Creation du Controleur (qui créera lui-même la TaquinFrame)
            new TaquinControleSwing(jeu);
        });
    }
}
