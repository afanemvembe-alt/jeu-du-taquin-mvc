package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import java.awt.*;
import javax.swing.*;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Panel affichant la grille de jeu composée de boutons.
 */
public class GrillePanel extends JPanel {
    /** Le tableau de boutons représentant les tuiles */
    private TuileButton[][] boutons;
    /** Référence vers le modèle pour lire l'état du jeu */
    private Taquin modele;
    /** Référence vers le contrôleur pour lier les boutons */
    private TaquinControleSwing controleur;
    /** Booléen indiquant si on est en mode image ou non */
	private boolean modeImage = false;
	/** Tableau contenant les images découpées en morceaux (image-lignes-colonnes) */
	private BufferedImage[][][] imagesDecoupees;
	/** Liste des images entières */
	private BufferedImage[] imagesOriginales;
	/** Indice de l'image courante */
	private int imageCourante = 0;
    /**
     * Constructeur de la grille.
     * @param modele le modèle du jeu
     * @param controleur le contrôleur Swing
     */
    public GrillePanel(Taquin modele, TaquinControleSwing controleur) {
        this.modele = modele;
        this.controleur = controleur;

        int n = modele.getLigne();
        int m = modele.getColonne();
        this.setLayout(new GridLayout(n, m, 0, 0));

        boutons = new TuileButton[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boutons[i][j] = new TuileButton(i, j, controleur);
                this.add(boutons[i][j]);
            }
        }
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				rafraichir();
			}
		});
        chargerImages();
        rafraichir();
        
    }
    
    /**
	 * Indique si le jeu est actuellement en mode image.
	 * @return true si le mode image est activé, false sinon
	 */
    public boolean estModeImage() {
		return modeImage;
	}
	
	/**
	 * Retourne l'image originale actuellement utilisée pour le mode image.
	 * @return l'image courante
	 */
	public BufferedImage getImageOriginaleCourante() {
		return imagesOriginales[imageCourante];
	}

    /**
     * Met à jour l'affichage des boutons selon l'état du modèle.
     * Gère l'affichage des valeurs numériques ou des morceaux d'image
	 * selon le mode courant, ainsi que la visibilité des tuiles.
     */
    public void rafraichir() {
		int nbColonnes = modele.getColonne();

		for (int i = 0; i < modele.getLigne(); i++) {
			for (int j = 0; j < modele.getColonne(); j++) {
				int val = modele.getValeur(i, j);

				if (val == 0) {
					boutons[i][j].setText("");
					boutons[i][j].setIcon(null);
					boutons[i][j].setVisible(false);
				} else {
					boutons[i][j].setVisible(true);

					if (modeImage) {
						boutons[i][j].setBorderPainted(false);
						boutons[i][j].setContentAreaFilled(false);
						boutons[i][j].setOpaque(false);

						int ligneImage = (val - 1) / nbColonnes;
						int colonneImage = (val - 1) % nbColonnes;

						BufferedImage morceau = imagesDecoupees[imageCourante][ligneImage][colonneImage];

						int largeurBouton = boutons[i][j].getWidth();
						int hauteurBouton = boutons[i][j].getHeight();

						if (largeurBouton <= 0 || hauteurBouton <= 0) {
							largeurBouton = 100;
							hauteurBouton = 100;
						}

						Image imageRedimensionnee = morceau.getScaledInstance(
							largeurBouton,
							hauteurBouton,
							Image.SCALE_SMOOTH
						);

						boutons[i][j].setIcon(new ImageIcon(imageRedimensionnee));
						boutons[i][j].setText("");
						boutons[i][j].setBackground(null);

					} else {
						boutons[i][j].setBorderPainted(true);
						boutons[i][j].setContentAreaFilled(true);
						boutons[i][j].setOpaque(true);
						boutons[i][j].setIcon(null);
						boutons[i][j].setText(String.valueOf(val));

						if (modele.estPossible(i, j)) {
							boutons[i][j].setBackground(new Color(144, 238, 144));
						} else {
							boutons[i][j].setBackground(null);
						}
					}
				}
			}
		}

		revalidate();
		repaint();
	}
    
    /**
	 * Charge les images depuis le dossier /images et les découpe en sous-images
	 * correspondant aux cases du taquin.
	 * Chaque image est découpée en n × m morceaux.
	 */
    private void chargerImages() {
		int n = modele.getLigne();
		int m = modele.getColonne();

		String[] nomsImages = {
			"/fr/unicaen/l2/afane_adji_koutchade/taquin/images/image1.jpg",
			"/fr/unicaen/l2/afane_adji_koutchade/taquin/images/image2.jpg",
			"/fr/unicaen/l2/afane_adji_koutchade/taquin/images/image3.jpg",
			"/fr/unicaen/l2/afane_adji_koutchade/taquin/images/image4.jpg"
		};

		imagesDecoupees = new BufferedImage[nomsImages.length][n][m];
		imagesOriginales = new BufferedImage[nomsImages.length];

		for (int k = 0; k < nomsImages.length; k++) {
			try {
				BufferedImage image = ImageIO.read(getClass().getResource(nomsImages[k]));
				imagesOriginales[k] = image;

				int largeurCase = image.getWidth() / m;
				int hauteurCase = image.getHeight() / n;

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						imagesDecoupees[k][i][j] = image.getSubimage(
							j * largeurCase,
							i * hauteurCase,
							largeurCase,
							hauteurCase
						);
					}
				}
			} catch (IOException | IllegalArgumentException e) {
				System.out.println("Erreur chargement image : " + nomsImages[k]);
			}
		}
	}
		
	/**
	 * Active ou désactive le mode image, puis met à jour l'affichage
	 * de la grille.
	 */
	public void changerModeImage() {
		modeImage = !modeImage;
		rafraichir();
	}

	/**
	 * Passe à l'image suivante parmi les images disponibles, puis
	 * met à jour l'affichage de la grille.
	 */
	public void imageSuivante() {
		imageCourante = (imageCourante + 1) % imagesDecoupees.length;
		rafraichir();
	}
}
