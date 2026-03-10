package taquin.modele; 

public class Taquin{
    private int ligne;
    private int colonne;
    private int [][] tableau;

	//  on stocke la position de la case vide (le 0) pour savoir quoi déplacer
    private int videL;
    private int videC;

    public Taquin(int line, int row){
         this.ligne = line;
         this.colonne = row;
        int [][] table= new int[line][row];
        int valeur= 1;
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                table[i][j]= valeur;
                valeur++;
            }
        }
        table[line-1][row-1]= 0;
        this.tableau= table;


        // Initiliser la position du vide (en bas à droite au départ)
        this.videL = line - 1;
        this.videC = row - 1;

    }
	// deplacement de la piece situé en (l ,c) vers la case vide si elle est adjacente  et return true si le mouvement est validé et effectuer
	/**
	 * verifier si la case est bien dans les limites du tableau
	 * calcul de la distance avec la case vide 
	 * deplacement est possible si la case est à coté du vide (pas en diagonale)
	 * echange des valeurs 
	 * mettre à jour la position 
	 */
	// il y'a aussi le melange du puzzle par des glissements 




	// méthode pour les Getters par ce que la vue aura besoin de regarder dans le modele pour savoir quels chiffres afficher
	public int getValeur(int l, int c) { return tableau[l][c]; }
	public int getLigne() { return ligne; }
	public int getColonne() { return colonne; }


	// est possible est pour verifier si l'echange ou le mouvement est posssible 
	public boolean estPossible(int ligne, int colonne){
		return true;
	}
	
	// pour echanger 2 valeurs (necessairement la valeur 0)
	public void echange(int valeur1, int valeur2){
		//
	}

	//monter ,descendre, gauche, droite
	public void monter(){
		//
	}
	public void descendre(){
		//
	}
	public void gauche(){
		//
	}
	public void droite(){
		//
	}

	public boolean fini(){
		return false;
	}


    public String toString(){
        String res= "";
        for(int i=0; i<this.tableau.length; i++){
            for(int j=0; j<this.tableau[i].length; j++){
                res+=this.tableau[i][j]+" ";
            }
            res+="\n";
        }
        return res;
    }

}
