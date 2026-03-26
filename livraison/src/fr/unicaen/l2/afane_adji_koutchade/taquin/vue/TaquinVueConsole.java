package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.util.EcouteurTaquin;


/**
 * Vue console du Taquin implémentant l'écouteur du modèle.
 */
public class TaquinVueConsole implements EcouteurTaquin {
    private Taquin jeu;
/**
     * Constructeur de la vue console.
     * @param jeu le modèle à observer
     */
    public TaquinVueConsole(Taquin jeu) {
        this.jeu = jeu;
        this.jeu.ajouterEcouteur(this); // S'abonne au modèle
    }

    @Override
    public void taquinUpdated(Object source) {
        afficher(); // Se redessine quand le modèle change
    }
/**
     * Affiche la grille actuelle dans le terminal.
     */
    public void afficher() {
        // ... (Ton code d'affichage avec les +----+)
        System.out.println(jeu.toString()); 
    }
}