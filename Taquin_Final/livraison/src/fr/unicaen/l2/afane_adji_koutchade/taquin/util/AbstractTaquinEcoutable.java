package fr.unicaen.l2.afane_adji_koutchade.taquin.util;
import java.util.*;

/**
 * Classe abstraite gérant les écouteurs du jeu de Taquin.
 */
public abstract class AbstractTaquinEcoutable {
    private List<EcouteurTaquin> ecouteurs = new ArrayList<>();
   /** Constructeur de base pour les classes filles. */
    protected AbstractTaquinEcoutable() {}
    /**
     * Ajoute un écouteur à la liste.
     * @param e l'écouteur à ajouter
     */
    public void ajouterEcouteur(EcouteurTaquin e) { this.ecouteurs.add(e); }

    /**
     * Retire un écouteur de la liste.
     * @param e l'écouteur à retirer
     */
    public void retraitEcouteur(EcouteurTaquin e) { this.ecouteurs.remove(e); }

    /**
     * Notifie tous les écouteurs d'un changement de l'état du jeu.
     */
    protected void taquinChangement() {
        for (EcouteurTaquin e : this.ecouteurs) {
            e.taquinUpdated(this);
        }
    }
}
