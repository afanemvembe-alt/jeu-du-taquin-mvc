package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import java.awt.*;
import javax.swing.*;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Panel affichant la grille de jeu composée de boutons.
 */
public class GrillePanel extends JPanel {
    private TuileButton[][] boutons;
    private Taquin modele;
    private TaquinControleSwing controleur;
    
    private boolean modeImage = false;
    private BufferedImage[][][] imagesDecoupees;
    private BufferedImage[] imagesOriginales;
    private int imageCourante = 0;

    public GrillePanel(Taquin modele, TaquinControleSwing controleur) {
        this.modele = modele;
        this.controleur = controleur;

        int n = modele.getLigne();
        int m = modele.getColonne();
        this.setLayout(new GridLayout(n, m, 2, 2));

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
                URL imgUrl = getClass().getResource(nomsImages[k]);
                if (imgUrl != null) {
                    BufferedImage image = ImageIO.read(imgUrl);
                    imagesOriginales[k] = image;
                    int largeurCase = image.getWidth() / m;
                    int hauteurCase = image.getHeight() / n;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            imagesDecoupees[k][i][j] = image.getSubimage(j * largeurCase, i * hauteurCase, largeurCase, hauteurCase);
                        }
                    }
                }
            } catch (IOException | IllegalArgumentException e) {
                System.err.println("Erreur chargement : " + nomsImages[k]);
            }
        }
    }

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
                    if (modeImage && imagesDecoupees[imageCourante][0][0] != null) {
                        boutons[i][j].setBorderPainted(false);
                        boutons[i][j].setContentAreaFilled(false);
                        int ligImg = (val - 1) / nbColonnes;
                        int colImg = (val - 1) % nbColonnes;
                        BufferedImage morceau = imagesDecoupees[imageCourante][ligImg][colImg];
                        if (morceau != null) {
                            int w = Math.max(boutons[i][j].getWidth(), 50);
                            int h = Math.max(boutons[i][j].getHeight(), 50);
                            Image rescaled = morceau.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                            boutons[i][j].setIcon(new ImageIcon(rescaled));
                            boutons[i][j].setText("");
                        }
                    } else {
                        boutons[i][j].setBorderPainted(true);
                        boutons[i][j].setContentAreaFilled(true);
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
        this.revalidate();
        this.repaint();
    }

    public boolean estModeImage() {
        return modeImage;
    }

    public BufferedImage getImageOriginaleCourante() {
        return (imagesOriginales != null) ? imagesOriginales[imageCourante] : null;
    }

    public void changerModeImage() {
        this.modeImage = !this.modeImage;
        rafraichir();
    }

    public void imageSuivante() {
        imageCourante = (imageCourante + 1) % imagesDecoupees.length;
        rafraichir();
    }
}