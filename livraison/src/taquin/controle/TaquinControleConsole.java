package taquin.controle;

import java.util.Scanner;
import taquin.modele.*;

/**
 * Classe de contrôle pour le jeu de Taquin en console.
 * Elle permet de gérer les déplacements du joueur et 
 * d'afficher l'état du plateau dans la console.
 */
public class TaquinControleConsole {
    /**
     * Instance du jeu de Taquin manipulée par le contrôleur.
     */
    public Taquin jeu;
    /**
     * Constructeur du contrôleur.
     * Initialise le contrôleur avec un jeu existant.
     * 
     * @param game instance du jeu de Taquin à contrôler
     */
    public TaquinControleConsole(Taquin game){
        this.jeu = game;
    }
    
    /**
     * Lance le jeu en console.
     * Affiche le plateau actuel, demande à l'utilisateur 
     * de saisir les déplacements, et met à jour le plateau
     * jusqu'à ce que le puzzle soit terminé.
     * Les touches reconnues sont :
     * z : monter
     * w : descendre
     * q : gauche
     * s : droite
     * Une touche invalide affiche un message d'erreur.
     */
    public void jouer(){
		this.jeu.melanger(20);
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.jeu);
        System.out.println("Deplacement (z = haut, w = bas, q = droite,  s = gauche) : ");
        while (!(this.jeu.fini())){
			System.out.print("Votre mouvement : ");
            char c = scanner.nextLine().charAt(0);
            if (c == 'z') this.jeu.monter();
            else if (c == 'w') jeu.descendre();
            else if (c == 'q') jeu.droite();
            else if (c == 's') jeu.gauche();
            else System.out.println("Touche invalide ! Reprenez!");
            System.out.println(this.jeu);
        }
        System.out.println(jeu);
        System.out.println("Bravo, puzzle termine !");
    }
}
