package taquin.vue;

/**
 * Interface pour notifier le contrôleur quand une tuile est cliquée.
 * Permet à la Vue de rester indépendante du modèle.
 */
public interface VueListener {
    void clicTuile(int ligne, int colonne);
}