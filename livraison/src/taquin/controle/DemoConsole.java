package taquin.controle;

import taquin.modele.*;


/*
 * Classe de démonstration permettant de lancer le jeu du taquin
 * en mode console. 
 * Cette classe contient la méthode principale (main) qui initialise
 * le jeu avec un plateau donné et démarre l'exécution.*/

public class DemoConsole{
 /**
     * Méthode principale qui lance le programme.
     * Elle cree un plateau de taquin avec des dimensions definies,
     * initialise le contrôleur console, puis demarre le jeu.
     */
	public static void main(String[] args){
        Taquin tableau= new Taquin(4, 5);
        TaquinControleConsole jeu= new TaquinControleConsole(tableau);
        jeu.jouer();
    }
}
