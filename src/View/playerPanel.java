package src.View;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;
import src.model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class playerPanel extends JLayeredPane {
    JPanel panel;
    public JLabel playerStats;
    public JPanel cardsPanel;
    JPanel score;
    public int playerScore =0;
    public JLabel statues;
    JButton wallPaintings;
    public JPanel lastPlayedCardsPanel;
    public JLabel timerInstance;
    wallPaintingsWin win = new wallPaintingsWin();
    public playerPanel(Player player) {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        lastPlayedCardsPanel = new JPanel(null);
        lastPlayedCardsPanel.setBounds(660,15,350,100);

        JLabel knossosC = new JLabel("Κνωσσός", SwingConstants.CENTER);
        knossosC.setBounds(3,0,67,95);
        knossosC.setBorder(BorderFactory.createLineBorder(Color.RED,3));

        JLabel maliaC = new JLabel("Μάλια", SwingConstants.CENTER);
        maliaC.setBounds(83,0,67,95);
        maliaC.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));

        JLabel phaistosC = new JLabel("Φαιστός", SwingConstants.CENTER);
        phaistosC.setBounds(166,0,67,95);
        phaistosC.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        JLabel zakrosC = new JLabel("Ζάκρος", SwingConstants.CENTER);
        zakrosC.setBounds(249,0,67,95);
        zakrosC.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));

        lastPlayedCardsPanel.add(knossosC);
        lastPlayedCardsPanel.add(maliaC);
        lastPlayedCardsPanel.add(phaistosC);
        lastPlayedCardsPanel.add(zakrosC);

        score = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel playerScoreT = new JLabel("Το Σκορ Μου: " + playerScore + " Πόντοι");
        playerScoreT.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        score.add(playerScoreT);

        JButton wallPaintings = new JButton("Οι Τοιχογραφίες μου");
        wallPaintings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                win.dispose();
                win.setTitle("Τοιχογραφίες Παικτη " + player.getName());
                win.setVisible(true);
            }
        });
        score.add(wallPaintings);

        JLabel statues = new JLabel("Αγαλματάκια: 0");
        statues.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        score.add(statues);
        ImageIcon originalImage = new ImageIcon("src/assets/images/findings/snakes.jpg");
        Image scaledStatue = originalImage.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);
        JLabel statueImage = new JLabel(new ImageIcon(scaledStatue));
        score.add(statueImage);
        score.setBounds(1000,10,170,130);
        timerInstance = new JLabel("Χρονος: 30");
        timerInstance.setFont(new Font("Arial", Font.BOLD, 13));
        timerInstance.setBounds(1180,5,80,50);
        add(timerInstance, JLayeredPane.DEFAULT_LAYER);
        add(lastPlayedCardsPanel, JLayeredPane.DEFAULT_LAYER);
        add(panel, JLayeredPane.DEFAULT_LAYER);
        add(cardsPanel, JLayeredPane.PALETTE_LAYER);
        add(score, JLayeredPane.PALETTE_LAYER);
    }
}
