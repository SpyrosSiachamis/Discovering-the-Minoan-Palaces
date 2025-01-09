package src.View;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;
import src.model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class playerPanel extends JLayeredPane {
    Player plr;
    JPanel panel;
    public JLabel playerStats;
    public JPanel cardsPanel;
    JPanel score;
    public int playerScore =0;
    public JLabel statues;
    public int amountStats;
    JButton wallPaintings;
    public JPanel lastPlayedCardsPanel;
    public JLabel timerInstance;
    wallPaintingsWin win = new wallPaintingsWin();
    public JLabel diskos;
    public JLabel ring;
    public JLabel kosmima;
    public JLabel ruto;
    JLabel knossosC = new JLabel();
    JLabel maliaC = new JLabel();
    JLabel phaistosC = new JLabel();
    JLabel zakrosC = new JLabel();

    public playerPanel(Player player) {
        this.plr = player;
        this.statues = new JLabel();
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
        lastPlayedCardsPanel.setBounds(660,15,320,130);

        knossosC = new JLabel("Κνωσσός", SwingConstants.CENTER);
        knossosC.setBounds(3,0,67,95);
        knossosC.setBorder(BorderFactory.createLineBorder(Color.RED,3));

        maliaC = new JLabel("Μάλια", SwingConstants.CENTER);
        maliaC.setBounds(83,0,67,95);
        maliaC.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));

        phaistosC = new JLabel("Φαιστός", SwingConstants.CENTER);
        phaistosC.setBounds(166,0,67,95);
        phaistosC.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        zakrosC = new JLabel("Ζάκρος", SwingConstants.CENTER);
        zakrosC.setBounds(249,0,67,95);
        zakrosC.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));

        lastPlayedCardsPanel.add(knossosC);
        lastPlayedCardsPanel.add(maliaC);
        lastPlayedCardsPanel.add(phaistosC);
        lastPlayedCardsPanel.add(zakrosC);

        ImageIcon diskOriginal = new ImageIcon("src/assets/images/findings/diskos.jpg");
        Image scaledDisk = diskOriginal.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);

        ImageIcon kosmimaOg = new ImageIcon("src/assets/images/findings/kosmima.jpg");
        Image scaledKosmisma = kosmimaOg.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);

        ImageIcon ringOg = new ImageIcon("src/assets/images/findings/ring.jpg");
        Image scaledRing = ringOg.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);

        ImageIcon rutoOg = new ImageIcon("src/assets/images/findings/ruto.jpg");
        Image scaledRuto = rutoOg.getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH);

        diskos = new JLabel(new ImageIcon(scaledDisk));
        diskos.setBounds(176,87,50,50);
        kosmima = new JLabel(new ImageIcon(scaledKosmisma));
        kosmima.setBounds(90,87,50,50);
        ruto = new JLabel(new ImageIcon(scaledRuto));
        ruto.setBounds(259,87,50,50);
        ring = new JLabel(new ImageIcon(scaledRing));
        ring.setBounds(13,87,50,50);

        lastPlayedCardsPanel.add(ring, JLayeredPane.PALETTE_LAYER);
        lastPlayedCardsPanel.add(diskos, JLayeredPane.PALETTE_LAYER);
        lastPlayedCardsPanel.add(kosmima, JLayeredPane.PALETTE_LAYER);
        lastPlayedCardsPanel.add(ruto, JLayeredPane.PALETTE_LAYER);

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
        JLabel statues = new JLabel("Αγαλματάκια: " + amountStats);
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

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(JLabel playerStats) {
        this.playerStats = playerStats;
    }

    public JPanel getCardsPanel() {
        return cardsPanel;
    }

    public void setCardsPanel(JPanel cardsPanel) {
        this.cardsPanel = cardsPanel;
    }

    public JPanel getScore() {
        return score;
    }

    public void setScore(JPanel score) {
        this.score = score;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public JLabel getStatues() {
        return statues;
    }

    public void setStatues() {
        this.statues.setText("Αγαλματάκια: " + plr.getAmountOfStatues());
        this.statues.revalidate();
        this.statues.repaint();
        this.score.revalidate();
        this.score.repaint();
    }

    public JButton getWallPaintings() {
        return wallPaintings;
    }

    public void setWallPaintings(JButton wallPaintings) {
        this.wallPaintings = wallPaintings;
    }

    public JPanel getLastPlayedCardsPanel() {
        return lastPlayedCardsPanel;
    }

    public void setLastPlayedCardsPanel(JPanel lastPlayedCardsPanel) {
        this.lastPlayedCardsPanel = lastPlayedCardsPanel;
    }

    public JLabel getTimerInstance() {
        return timerInstance;
    }

    public void setTimerInstance(JLabel timerInstance) {
        this.timerInstance = timerInstance;
    }

    public wallPaintingsWin getWin() {
        return win;
    }

    public void setWin(wallPaintingsWin win) {
        this.win = win;
    }

    public JLabel getDiskos() {
        return diskos;
    }

    public void setDiskos(JLabel diskos) {
        this.diskos = diskos;
    }

    public JLabel getRing() {
        return ring;
    }

    public void setRing(JLabel ring) {
        this.ring = ring;
    }

    public JLabel getKosmima() {
        return kosmima;
    }

    public void setKosmima(JLabel kosmima) {
        this.kosmima = kosmima;
    }

    public JLabel getRuto() {
        return ruto;
    }

    public void setRuto(JLabel ruto) {
        this.ruto = ruto;
    }

    public JLabel getZakrosC() {
        return zakrosC;
    }

    public void setZakrosC(JLabel zakrosC) {
        this.zakrosC = zakrosC;
    }

    public JLabel getPhaistosC() {
        return phaistosC;
    }

    public void setPhaistosC(JLabel phaistosC) {
        this.phaistosC = phaistosC;
    }

    public JLabel getMaliaC() {
        return maliaC;
    }

    public void setMaliaC(JLabel maliaC) {
        this.maliaC = maliaC;
    }

    public JLabel getKnossosC() {
        return knossosC;
    }

    public void setKnossosC(JLabel knossosC) {
        this.knossosC = knossosC;
    }
}