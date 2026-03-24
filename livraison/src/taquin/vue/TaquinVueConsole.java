package taquin.vue;

import taquin.modele.*;

public class TaquinVueConsole{
	
	private Taquin jeu;
	
	public TaquinVueConsole(Taquin jeu){
		this.jeu = jeu;
	}
	
	public void afficher(){
        int lignes = jeu.getLigne();
        int colonnes = jeu.getColonne();

        String ligneSep = "";
        for (int j = 0; j < colonnes; j++) {
            ligneSep += "+----";
        }
        ligneSep += "+";

        System.out.println(ligneSep);
        for (int i = 0; i < lignes; i++) {
            String ligneAff = "";
            for (int j = 0; j < colonnes; j++) {
                int val = jeu.getValeur(i, j);
                String caseTexte = (val == 0) ? "  " : String.format("%2d", val);
                ligneAff += "| " + caseTexte + " ";
            }
            ligneAff += "|";
            System.out.println(ligneAff);
            System.out.println(ligneSep);
        }
    }
}
