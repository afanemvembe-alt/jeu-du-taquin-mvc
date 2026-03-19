package taquin.vue;

import java.awt.*;
import javax.swing.*;
import taquin.modele.*;

/**
 * Fenêtre principale du Taquin.
 * Reçoit le modèle et le listener (contrôleur).
 * Gère l'affichage de la grille, du compteur de coups et des boutons.
 */
public class TaquinFrame extends JFrame {

    private Taquin modele;
    private VueListener listener;
    private GrillePanel grille;
    private JLabel labelCoups;
    private JButton btnRejouer;
    private JButton btnRetour;
    private JButton btnAide;
    private JButton btnIndice;

    /**
     * Constructeur de la fenêtre principale.
     * 
     * @param modele le modèle Taquin (logique du jeu)
     * @param listener le contrôleur qui implémente VueListener
     */
    public TaquinFrame(Taquin modele, VueListener listener) {
        this.modele = modele;
        this.listener = listener;

        // Layout principal
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30)); // fond sombre

        // --------------------
        // Grille du Taquin
        // --------------------
        grille = new GrillePanel(modele, listener);
        add(grille, BorderLayout.CENTER);

        // --------------------
        // Compteur de coups
        // --------------------
        labelCoups = new JLabel("Coups : 0 | Indices : 3");
        labelCoups.setFont(new Font("Arial", Font.BOLD, 16));
        labelCoups.setHorizontalAlignment(SwingConstants.CENTER);
        labelCoups.setForeground(Color.WHITE); // compteur en blanc
        add(labelCoups, BorderLayout.SOUTH);

        // --------------------
        // Panel des boutons
        // --------------------
        JPanel panelBoutons = new JPanel(new FlowLayout());

        btnRejouer = new JButton("Rejouer");
        btnRejouer.setBackground(new Color(144, 238, 144)); // vert clair
        btnRejouer.setForeground(Color.BLACK);

        btnRetour = new JButton("Retour");
        btnRetour.setBackground(new Color(255, 182, 193)); // rose clair
        btnRetour.setForeground(Color.BLACK);

        btnAide = new JButton("Aide");
        btnAide.setBackground(new Color(173, 216, 230)); // bleu clair
        btnAide.setForeground(Color.BLACK);

        btnIndice = new JButton("Indice (3)");
        btnIndice.setBackground(new Color(255, 255, 102)); // jaune clair
        btnIndice.setForeground(Color.BLACK);

        // Ajouter les boutons au panel
        panelBoutons.add(btnRejouer);
        panelBoutons.add(btnRetour);
        panelBoutons.add(btnAide);
        panelBoutons.add(btnIndice);

        add(panelBoutons, BorderLayout.NORTH);

        // --------------------
        // Actions des boutons
        // --------------------
        btnRejouer.addActionListener(e -> {
            modele.melanger(50); // mélange le puzzle
            rafraichir();         // met à jour la grille et le compteur
        });

        btnRetour.addActionListener(e -> {
            modele.retour(); 
            rafraichir();
        });

        btnAide.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "But : remettre les nombres dans l'ordre.\n" +
                "Clique sur une tuile voisine du vide.",
                "Aide", JOptionPane.INFORMATION_MESSAGE);
        });

        btnIndice.addActionListener(e -> {
            if (modele.getIndicesRestants() > 0) {
                modele.utiliserIndice();
                rafraichir();
            } else {
                JOptionPane.showMessageDialog(this, "Plus d'indices disponibles !",
                        "Indice", JOptionPane.WARNING_MESSAGE);
            }
        });

        // --------------------
        // Propriétés de la fenêtre
        // --------------------
        setTitle("Jeu du Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Met à jour l'affichage de la grille et du compteur de coups.
     * Appelée par le contrôleur après chaque action sur le modèle.
     */
    public void rafraichir() {
        grille.rafraichir();
        labelCoups.setText("Coups : " + modele.getNbCoups() + " | Indices : " + modele.getIndicesRestants());

        if (modele.fini()) {
            JOptionPane.showMessageDialog(this,
                    "Bravo ! Vous avez terminé le Taquin en " + modele.getNbCoups() + " coups !",
                    "Victoire", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}