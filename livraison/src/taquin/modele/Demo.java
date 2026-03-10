package modele;

public class Demo{
    public static void main(String[] args){
        Taquin tableau= new Taquin(4, 5);
        System.out.println(tableau);
    }
}





/**Le sujet demande une réalisation "intégralement MVC" avec un "modèle totalement indépendant de l'interface graphique". 
 * Le fichier Taquin.java est ton Modèle. Il contient uniquement des chiffres et de la logique (le "cerveau").
 * Le JPanel (ou la JFrame) appartient à la Vue. Tu devras créer une autre classe (par exemple TaquinGraphique.java) qui, 
 * elle, contiendra les JPanel et les boutons.
*/