package taquin.controle;

import taquin.modele.*;

public class Demo{
	public static void main(String[] args){
        Taquin tableau= new Taquin(4, 5);
        TaquinControleConsole jeu= new TaquinControleConsole(tableau);
        jeu.jouer();
    }
}
