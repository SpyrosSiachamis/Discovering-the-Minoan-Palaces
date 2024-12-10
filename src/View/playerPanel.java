package src.View;

import src.model.Player;

import javax.swing.*;
import java.awt.*;

public class playerPanel extends JLayeredPane {
    JPanel panel;
    public JLabel playerStats;
    public JPanel cardsPanel;
    public JLabel score;
    public JLabel statues;
    JButton wallPaintings;
    public playerPanel(Player player) {
        panel = new JPanel();
        setLayout(null);
        setSize(1280,150);
        if (player.getName().equals("1")){
            setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
        else if (player.getName().equals("2")){
            setBorder(BorderFactory.createLineBorder(Color.green, 3));
        }
        else{
            setBorder(BorderFactory.createLineBorder(Color.black, 3)); //DEFAULT VALUE
        }

        playerStats = new JLabel("Παίκτης " + player.getName() + " - Διαθέσιμα Πιόνια: " + player.amountOfArchaeologists() + " Αρχαιολόγοι και " + player.hasThesseus() + " Θησέας");
        panel.add(playerStats);
        panel.setBounds(3,122,395,25);

        cardsPanel = new JPanel(new GridLayout(1,8));
        cardsPanel.setBounds(20,15,600,100);

        add(panel, JLayeredPane.DEFAULT_LAYER);
        add(cardsPanel, JLayeredPane.PALETTE_LAYER);

    }
}
