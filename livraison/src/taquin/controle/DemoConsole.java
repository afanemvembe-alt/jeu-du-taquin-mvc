package taquin.controle;

import taquin.modele.*;

public class DemoConsole{
	public static void main(String[] args){
        Taquin tableau= new Taquin(4, 5);
        TaquinControleConsole jeu= new TaquinControleConsole(tableau);
        jeu.jouer();
    }
}
