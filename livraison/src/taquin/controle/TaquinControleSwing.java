package taquin.controle;

import taquin.modele.*;
import taquin.vue.*;

public class TaquinControleSwing implements VueListener {

    private Taquin modele;
    private TaquinFrame frame;

    public TaquinControleSwing(Taquin modele) {
        this.modele = modele;
        this.frame = new TaquinFrame(modele, this);
    }

    @Override
    public void clicTuile(int l, int c) {
        if (modele.estPossible(l, c)) {
            modele.echange(l, c);
            frame.rafraichir();
        }
    }
}