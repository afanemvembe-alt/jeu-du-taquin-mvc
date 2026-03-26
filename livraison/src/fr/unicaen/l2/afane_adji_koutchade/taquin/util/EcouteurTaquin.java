package fr.unicaen.l2.afane_adji_koutchade.taquin.util;

/**
 * Interface pour les objets souhaitant écouter les changements du Taquin.
 */
public interface EcouteurTaquin {
    /**
     * Méthode appelée lors d'une modification du modèle.
     * @param source l'objet source du changement
     */
    void taquinUpdated(Object source);
}