package taquin.controle;

import java.util.Scanner;
import taquin.modele.*;
import taquin.vue.*;

/**
 * Contrôleur pour le jeu de Taquin en console.
 * Gère les déplacements du joueur et l'affichage du plateau dans la console.
 */
public class TaquinControleConsole {

    /** Instance du jeu de Taquin manipulée par le contrôleur */
    public Taquin jeu;

    /** Vue console associée au modèle */
    public TaquinVueConsole vue;

    /** Nombre de coups effectués */
    public int nbCoups= 0;
    
    /**Booléen qui controle la poursuite du jeu */
    public boolean poursuivre = true;

    /**
     * Constructeur du contrôleur console.
     * Initialise le contrôleur avec un jeu existant.
     * 
     * @param game instance du jeu de Taquin à contrôler
     */
    public TaquinControleConsole(Taquin game){
        this.jeu = game;
        this.vue = new TaquinVueConsole(this.jeu);
    }
    
    /**
     * Lance le jeu en console.
     * Affiche le plateau actuel, demande à l'utilisateur 
     * de saisir les déplacements, et met à jour le plateau
     * jusqu'à ce que le puzzle soit terminé.
     * Les touches reconnues sont :
     * z : monter
     * w : descendre
     * q : droite
     * s : gauche
     * Une touche invalide affiche un message d'erreur.
     */
    public void jouer(){
		while(this.poursuivre){
			this.jeu.melanger(20);
			Scanner scanner = new Scanner(System.in);
			this.vue.afficher();
			System.out.println("Deplacement (z = haut, w = bas, q = droite,  s = gauche) : ");
			while (!(this.jeu.fini())){
				char c = scanner.nextLine().charAt(0);
				System.out.println("Votre mouvement : ");
				if (c == 'z'){
					this.jeu.monter();
					this.nbCoups+=1;
				}
				else if (c == 'w'){
					this.jeu.descendre();
					this.nbCoups+=1;
				}
				else if (c == 'q'){
					this.jeu.droite();
					this.nbCoups+=1;
				}
				else if (c == 's'){
					this.jeu.gauche();
					this.nbCoups+=1;
				}
				else System.out.println("Touche invalide ! Reprenez!");
				this.vue.afficher();
			}
			this.vue.afficher();
			System.out.println("Bravo, puzzle termine !");
			System.out.println("Nombre de coups : "+nbCoups);
			System.out.println("Voulez vous rejouer O/N : ");
			char choix = scanner.nextLine().toUpperCase().charAt(0);
			this.poursuivre = (choix == 'O');
			this.nbCoups = 0;
		}
    }
}
