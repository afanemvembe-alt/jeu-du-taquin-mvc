package modele; 
public class Taquin{
    private int ligne;
    private int colonne;
    private int [][] tableau;

    public Taquin(int line, int row){
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
