package fr.unicaen.l2.afane_adji_koutchade.taquin.controleur;

import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;

/**
 * Classe de lancement pour la version en mode console du Taquin.
 */
public class DemoConsole {

    /** Constructeur par défaut. */
    public DemoConsole() {}

    /**
     * Point d'entrée principal pour la version console.
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        // Initialisation du modèle (4 lignes, 4 colonnes)
        Taquin modele = new Taquin(4, 4);
        // Initialisation du contrôleur
        TaquinControleConsole controleur = new TaquinControleConsole(modele);
        // Lancement du jeu
        controleur.jouer();
    }
}