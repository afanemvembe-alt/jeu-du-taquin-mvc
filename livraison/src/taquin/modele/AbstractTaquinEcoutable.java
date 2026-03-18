package taquin.modele;

import java.util.*;

public abstract class AbstractTaquinEcoutable implements TaquinEcoutable {

	public List<EcouteurTaquin> ecouteurs;

	public AbstractTaquinEcoutable() {
		this.ecouteurs = new ArrayList<>();
	}

	public void ajouterEcouteur(EcouteurTaquin e) {
		this.ecouteurs.add(e);
	}

	public void retraitEcouteur(EcouteurTaquin e) {
		this.ecouteurs.remove(e);
	}

	protected void taquinChangement() {
		for (EcouteurTaquin e : this.ecouteurs) {
			e.taquinUpdated(this);
		}
	}

}
