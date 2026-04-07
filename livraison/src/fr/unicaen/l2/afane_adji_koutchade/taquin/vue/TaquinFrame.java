package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.util.EcouteurTaquin;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Fenêtre principale du Taquin respectant le pattern Observer (Ecouteur).
 * Cette classe gère l'interface utilisateur globale, le panneau latéral
 * et les notifications provenant du modèle.
 */
public class TaquinFrame extends JFrame implements EcouteurTaquin {

    /** Référence vers le modèle de données */
    private Taquin modele;
    /** Référence vers le contrôleur pour les événements */
    private TaquinControleSwing controleur;
    /** Panel contenant la grille de boutons */
    private GrillePanel grille;
    /** Label affichant le score et les indices restants */
    private JLabel labelCoups;
    /** Bouton permettant d'annuler le dernier coup */
    private JButton btnRetour;
    /** Bouton permettant d'afficher l'image originale */
    private JButton btnApercu;
    /** Label utilisé pour afficher l'icône de l'aperçu */
    private JLabel labelApercu;
    /** Panneau conteneur pour l'affichage de l'aperçu */
    private JPanel panelApercu;

    /**
     * Constructeur de la fenêtre principale.
     * Initialise les composants graphiques et s'abonne aux changements du modèle.
     * @param modele le modèle de données du jeu
     * @param controleur le contrôleur gérant les interactions utilisateur
     */
    public TaquinFrame(Taquin modele, TaquinControleSwing controleur) {
        this.modele = modele;
        this.controleur = controleur;

        // Abonnement au modèle (Pattern Observer)
        this.modele.ajouterEcouteur(this);

        setTitle("Jeu du Taquin - Groupe Afane/Adji/Koutchade");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Initialisation de la grille de jeu
        grille = new GrillePanel(modele, controleur);
        grille.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(grille, BorderLayout.CENTER);

        // Affichage du compteur de coups et des indices
        labelCoups = new JLabel("Coups : 0 | Indices : 3");
        labelCoups.setFont(new Font("Arial", Font.BOLD, 16));
        labelCoups.setForeground(Color.WHITE);
        labelCoups.setHorizontalAlignment(SwingConstants.CENTER);
        labelCoups.setMaximumSize(new Dimension(200, 30));
        
        // Configuration du panneau latéral (Statistiques et Contrôles)
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(new Color(50, 50, 60));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        labelCoups.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLateral.add(labelCoups);

        // Ajout du bloc de boutons de commande
        JPanel panelBoutons = setupBoutons();
        panelBoutons.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLateral.add(panelBoutons);

        // Section dédiée à l'aperçu de l'image
        JLabel titreApercu = new JLabel("Apercu");
        titreApercu.setFont(new Font("Arial", Font.BOLD, 15));
        titreApercu.setForeground(Color.WHITE);
        titreApercu.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLateral.add(titreApercu);

        panelApercu = new JPanel(new BorderLayout());
        panelApercu.setPreferredSize(new Dimension(180, 180));
        panelApercu.setMaximumSize(new Dimension(180, 180));
        panelApercu.setMinimumSize(new Dimension(180, 180));
        panelApercu.setBackground(new Color(70, 70, 70));
        panelApercu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panelApercu.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        labelApercu = new JLabel("Aucune image", SwingConstants.CENTER);
        labelApercu.setForeground(Color.WHITE);
        labelApercu.setFont(new Font("Arial", Font.PLAIN, 14));
        labelApercu.setHorizontalAlignment(SwingConstants.CENTER);
        labelApercu.setVerticalAlignment(SwingConstants.CENTER);
        panelApercu.add(labelApercu, BorderLayout.CENTER);
        panelLateral.add(panelApercu);

        // Mise en place du défilement pour le panneau latéral si nécessaire
        JScrollPane scrollPane = new JScrollPane(panelLateral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(new Color(50, 50, 60));
        add(scrollPane, BorderLayout.EAST);

        // Paramètres de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(900, 600));
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        // Activation du focus pour la capture des touches clavier (ZQSD)
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Crée et configure l'ensemble des boutons d'action du jeu.
     * Applique les styles communs et désactive le focusable pour préserver
     * le contrôle clavier par la fenêtre principale.
     * @return un JPanel contenant les boutons de contrôle
     */
    private JPanel setupBoutons() {
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBackground(new Color(50, 50, 60));
        
        JButton btnRejouer = new JButton("Rejouer");
        btnRejouer.addActionListener(e -> modele.melanger(50));
        
        JButton btnModeImage = new JButton("Mode Image");
        btnModeImage.addActionListener(e -> {
            grille.changerModeImage();
            boolean modeImage = grille.estModeImage();
            btnApercu.setEnabled(modeImage);

            if (!modeImage) {
                labelApercu.setIcon(null);
                labelApercu.setText("Aucune image");
            }
        });

        JButton btnChangerImage = new JButton("Image Suivante");
        btnChangerImage.addActionListener(e -> grille.imageSuivante());
        
        btnRetour = new JButton("Retour");
        btnRetour.setEnabled(false);
        btnRetour.addActionListener(e -> modele.retour());
        
        JButton btnIndice = new JButton("Indice");
        btnIndice.addActionListener(e -> {
            if (modele.getIndicesRestants() > 0) {
                modele.utiliserIndice();
            } else {
                JOptionPane.showMessageDialog(this, "Plus d'indices !");
            }
        });
        
        btnApercu = new JButton("Apercu");
        btnApercu.setEnabled(false);
        btnApercu.addActionListener(e -> afficherApercu());
        
        Dimension tailleBouton = new Dimension(160, 35);

        JButton[] boutons = {
            btnRejouer, btnRetour, btnIndice,
            btnModeImage, btnChangerImage, btnApercu
        };

        // Configuration générique des boutons pour le design et le focus
        for (JButton bouton : boutons) {
            bouton.setPreferredSize(tailleBouton);
            bouton.setMaximumSize(tailleBouton);
            bouton.setMinimumSize(tailleBouton);
            bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
            bouton.setFocusPainted(false);
            
            // Empêche le bouton de capturer le focus afin que ZQSD fonctionne toujours
            bouton.setFocusable(false);
        }

        panelBoutons.add(btnRejouer);
        panelBoutons.add(Box.createVerticalStrut(10));
        panelBoutons.add(btnRetour);
        panelBoutons.add(Box.createVerticalStrut(10));
        panelBoutons.add(btnIndice);
        panelBoutons.add(Box.createVerticalStrut(10));
        panelBoutons.add(btnModeImage);
        panelBoutons.add(Box.createVerticalStrut(10));
        panelBoutons.add(btnChangerImage);
        panelBoutons.add(Box.createVerticalStrut(10));
        panelBoutons.add(btnApercu);

        return panelBoutons;
    }

    /**
     * Méthode déclenchée par le modèle lors d'un changement d'état.
     * Met à jour la grille, les compteurs et gère les conditions de victoire.
     * @param source l'objet ayant déclenché la mise à jour
     */
    @Override
    public void taquinUpdated(Object source) {
        grille.rafraichir();
        labelCoups.setText("Coups : " + modele.getNbCoups() + " | Indices : " + modele.getIndicesRestants());

        if (btnRetour != null) {
            btnRetour.setEnabled(modele.getNbCoups() > 0);
        }

        // Redonne impérativement le focus à la fenêtre pour les contrôles clavier
        this.requestFocusInWindow();

        // Vérification de l'état final
        if (modele.fini() && modele.getNbCoups() > 0) {
            JOptionPane.showMessageDialog(this, "Bravo ! Victoire en " + modele.getNbCoups() + " coups.");
        }
    }
    
    /**
     * Récupère l'image originale dans le GrillePanel et l'affiche redimensionnée
     * dans le label d'aperçu.
     */
    private void afficherApercu() { 
        BufferedImage image = grille.getImageOriginaleCourante(); 
        if (image != null) { 
            // Redimensionnement de l'aperçu pour s'adapter au panneau latéral
            Image imageRedimensionnee = image.getScaledInstance(160, 160, Image.SCALE_SMOOTH); 
            labelApercu.setText(""); 
            labelApercu.setIcon(new ImageIcon(imageRedimensionnee)); 
        } else { 
            labelApercu.setIcon(null); 
            labelApercu.setText("Aucune image"); 
        }
        panelApercu.revalidate();
        panelApercu.repaint();
    } 
}