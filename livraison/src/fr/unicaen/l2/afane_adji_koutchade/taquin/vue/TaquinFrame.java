package fr.unicaen.l2.afane_adji_koutchade.taquin.vue;

import java.awt.*;
import javax.swing.*;
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
        add(grille, BorderLayout.CENTER);

        // Compteur de coups
        labelCoups = new JLabel("Coups : 0 | Indices : 3");
        labelCoups.setFont(new Font("Arial", Font.BOLD, 16));
        labelCoups.setForeground(Color.WHITE);
        labelCoups.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelCoups, BorderLayout.SOUTH);

        // Configuration des boutons du menu
        setupBoutons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Initialise la barre d'outils avec les boutons d'action.
     */
    private void setupBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout());
        
        JButton btnRejouer = new JButton("Rejouer");
        btnRejouer.addActionListener(e -> modele.melanger(50));
        
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

        panelBoutons.add(btnRejouer);
        panelBoutons.add(btnRetour);
        panelBoutons.add(btnIndice);
        add(panelBoutons, BorderLayout.NORTH);
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
}