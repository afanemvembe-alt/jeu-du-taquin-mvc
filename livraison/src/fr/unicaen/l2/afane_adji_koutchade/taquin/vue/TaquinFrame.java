package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import fr.unicaen.l2.afane_adji_koutchade.taquin.modele.Taquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.util.EcouteurTaquin;
import fr.unicaen.l2.afane_adji_koutchade.taquin.controleur.TaquinControleSwing;

/**
 * Fenêtre principale du Taquin respectant le pattern Observer (Ecouteur).
 */
public class TaquinFrame extends JFrame implements EcouteurTaquin {

    /** Référence vers le modèle de données */
    private Taquin modele;
    /** Référence vers le contrôleur pour les événements */
    private TaquinControleSwing controleur;
    /** Panel contenant la grille de boutons */
    private GrillePanel grille;
    /** Label affichant le score et les indices */
    private JLabel labelCoups;
    /** Bouton permettant d'annuler le dernier coup */
    private JButton btnRetour;
	/** Bouton permettant d'afficher l'image utilisée */
    private JButton btnApercu;
    /** Label indiquant l'aperçu */
    private JLabel labelApercu;
    /** Panel dans lequel l'image courante sera affichée */
	private JPanel panelApercu;

    /**
     * Constructeur de la fenêtre principale.
     * @param modele le modèle du jeu
     * @param controleur le contrôleur des interactions
     */
    public TaquinFrame(Taquin modele, TaquinControleSwing controleur) {
        this.modele = modele;
        this.controleur = controleur;

        // Abonnement au modèle
        this.modele.ajouterEcouteur(this);

        setTitle("Jeu du Taquin - Groupe Afane/Adji/Koutchade");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Initialisation de la grille
        grille = new GrillePanel(modele, controleur);
        grille.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(grille, BorderLayout.CENTER);

        // Compteur de coups
        labelCoups = new JLabel("Coups : 0 | Indices : 3");
        labelCoups.setFont(new Font("Arial", Font.BOLD, 16));
        labelCoups.setForeground(Color.WHITE);
        labelCoups.setHorizontalAlignment(SwingConstants.CENTER);
        labelCoups.setMaximumSize(new Dimension(200, 30));
        
        // Panneau latéral
		JPanel panelLateral = new JPanel();
		panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
		panelLateral.setBackground(new Color(50, 50, 60));
		panelLateral.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		labelCoups.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLateral.add(labelCoups);

		// Boutons
		JPanel panelBoutons = setupBoutons();
		panelBoutons.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLateral.add(panelBoutons);

		// Titre aperçu
		JLabel titreApercu = new JLabel("Apercu");
		titreApercu.setFont(new Font("Arial", Font.BOLD, 15));
		titreApercu.setForeground(Color.WHITE);
		titreApercu.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLateral.add(titreApercu);

		// Panel aperçu et label de l'aperçu
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

		// Ajout du panneau latéral à droite
		JScrollPane scrollPane = new JScrollPane(panelLateral);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setBackground(new Color(50, 50, 60));
		add(scrollPane, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
		setMinimumSize(new Dimension(900, 600));
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setVisible(true);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Initialise la barre d'outils avec les boutons d'action.
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
        
        // Correction : On utilise le champ de la classe, pas une variable locale
        btnRetour = new JButton("Retour");
        btnRetour.setEnabled(false); // Désactivé au début
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

		for (JButton bouton : boutons) {
			bouton.setPreferredSize(tailleBouton);
			bouton.setMaximumSize(tailleBouton);
			bouton.setMinimumSize(tailleBouton);
			bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
			bouton.setFocusPainted(false);
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
     * Méthode appelée lors d'une notification du modèle.
     * @param source la source de l'événement
     */
    @Override
    public void taquinUpdated(Object source) {
        grille.rafraichir();
        labelCoups.setText("Coups : " + modele.getNbCoups() + " | Indices : " + modele.getIndicesRestants());

        // Mise à jour de l'état du bouton retour
        if (btnRetour != null) {
            btnRetour.setEnabled(modele.getNbCoups() > 0);
        }

        if (modele.fini() && modele.getNbCoups() > 0) {
            JOptionPane.showMessageDialog(this, "Bravo ! Victoire en " + modele.getNbCoups() + " coups.");
        }
    }
    
    /**
	 * Affiche dans le panneau d'aperçu l'image actuellement utilisée
	 * en mode image.
	 */
    private void afficherApercu() { 
		BufferedImage image = grille.getImageOriginaleCourante(); 
		if (image != null) { 
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
