package taquin.modele;


public class TaquinDemo {
    public static void main(String[] args) {
        Taquin tableau = new Taquin(4, 5);
        tableau.melanger(100);
        System.out.println (tableau);
        System.out.println(tableau.fini());

        Taquin tableau2 = new Taquin(4, 5);
        System.out.println (tableau2);
        System.out.println(tableau2.fini());

        Taquin tableau3 = new Taquin(7, 5);
        tableau3.melanger(100);
        System.out.println (tableau3);
        System.out.println(tableau3.fini());

        Taquin tableau4 = new Taquin(4, 2);
        System.out.println (tableau4);
        System.out.println(tableau4.fini());
    }
}
