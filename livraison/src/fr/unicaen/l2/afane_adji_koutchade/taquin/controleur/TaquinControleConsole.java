package fr.unicaen.l2.afane_adji_koutchade.taquin.controleur;

import java.util.Scanner;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.vue.TaquinVueConsole;

/**
 * Contrôleur pour le jeu de Taquin en mode Console.
 * Respecte l'architecture MVC : il reçoit les entrées et modifie le modèle.
 */
public class TaquinControleConsole {

    /** Le modèle du jeu */
    private Taquin jeu;

    /** La vue console (affichage) */
    private TaquinVueConsole vue;

    /** État de la partie */
    private boolean poursuivre = true;

    /**
     * Constructeur du contrôleur console.
     * @param game l'instance du modèle de jeu
     */
    public TaquinControleConsole(Taquin game) {
        this.jeu = game;
        this.vue = new TaquinVueConsole(this.jeu);
    }
    
    /**
     * Boucle principale du jeu en console.
     */
    public void jouer() {
        Scanner scanner = new Scanner(System.in);
        
        while (this.poursuivre) {
            this.jeu.melanger(20);
            this.vue.afficher();
            
            while (!(this.jeu.fini())) {
                System.out.println("Déplacement (z=haut, s=bas, q=gauche, d=droite) : ");
                String input = scanner.nextLine();
                
                if (input.isEmpty()) continue;
                
                char c = input.toLowerCase().charAt(0);
                
                switch (c) {
                    case 'z': this.jeu.monter(); break;
                    case 's': this.jeu.descendre(); break;
                    case 'q': this.jeu.gauche(); break;
                    case 'd': this.jeu.droite(); break;
                    default: 
                        System.out.println("Touche invalide ! (Z, Q, S, D)");
                        continue;
                }
                // Note : On n'appelle pas explicitement vue.afficher() ici 
                // car la vue est un ÉCOUTEUR qui s'affiche seule quand le modèle change.
            }
            
            System.out.println("Bravo, puzzle terminé en " + jeu.getNbCoups() + " coups !");
            System.out.println("Voulez-vous rejouer ? (O/N) : ");
            String choix = scanner.nextLine().toUpperCase();
            this.poursuivre = (!choix.isEmpty() && choix.charAt(0) == 'O');
        }
        System.out.println("Merci d'avoir joué !");
    }
}