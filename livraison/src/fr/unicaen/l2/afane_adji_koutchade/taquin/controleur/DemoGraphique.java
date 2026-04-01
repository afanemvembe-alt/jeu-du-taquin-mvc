package fr.unicaen.l2.afane_adji_koutchade.taquin.controleur;

import javax.swing.SwingUtilities;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;

/**
 * Point d'entrée pour la version avec interface graphique (Swing).
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
            
            // 2. Mélange
            jeu.melanger(100);

            // 3. Création du Contrôleur (qui créera lui-même la TaquinFrame)
            new TaquinControleSwing(jeu);
        });
    }
}
