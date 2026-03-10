package taquin.modele;

import java.util.Scanner; // Importation pour lire les entrées du clavier

public class Demo {
    public static void main(String[] args) {
        Taquin tableau = new Taquin(4, 5); 
        
        // On mélange le puzzle pour qu'il ne soit pas déjà résolu
        // On effectue 100 mouvements aléatoires valides
        tableau.melanger(100); 

        // Initialisation du scanner pour lire les touches Z, Q, S, D
        Scanner sc = new Scanner(System.in);
        String choix = "";

        System.out.println("--- BIENVENUE DANS LE JEU DU TAQUIN ---");
        System.out.println("Commandes : Z (Haut), S (Bas), Q (Gauche), D (Droite), STOP (Quitter)");

        // Boucle de jeu : s'arrete si l'utilisateur tape "STOP"
        while (!choix.equalsIgnoreCase("STOP")) {
            
            // On affiche l'état actuel du tableau
            System.out.println("\nGrille actuelle :");
            System.out.println(tableau); 

            // Lecture de la commande utilisateur
            System.out.print("Votre mouvement : ");
            choix = sc.next().toUpperCase(); // On force en majuscule pour simplifier

            // On appelle la méthode correspondante du modele
            switch (choix) {
                case "Z":
                    tableau.monter(); // Deplace la piece sous le vide vers le haut
                    break;
                case "S":
                    tableau.descendre(); // Deplace la pièce au dessus du vide vers le bas
                    break;
                case "Q":
                    tableau.gauche(); // Deplace la piece à droite du vide vers la gauche
                    break;
                case "D":
                    tableau.droite(); // Deplace la piece à gauche du vide vers la droite
                    break;
                case "STOP":
                    System.out.println("Partie interrompue.");
                    break;
                default:
                    System.out.println("Commande invalide ! Utilisez Z, Q, S ou D.");
                    break;
            }
        }

        sc.close();
    }
}














/**Le sujet demande une réalisation "intégralement MVC" avec un "modèle totalement indépendant de l'interface graphique". 
 * Le fichier Taquin.java est ton Modèle. Il contient uniquement des chiffres et de la logique (le "cerveau").
 * Le JPanel (ou la JFrame) appartient à la Vue. Tu devras créer une autre classe (par exemple TaquinGraphique.java) qui, 
 * elle, contiendra les JPanel et les boutons.
*/
