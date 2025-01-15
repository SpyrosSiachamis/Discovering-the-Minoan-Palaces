package src.View;

import src.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
/**
 * The {@code playerPanel} class represents the visual panel displaying the player's stats, cards, last played cards,
 * and other relevant information during the game. It provides an interactive user interface to display player-specific
 * information like their score, available pawns, and a timer.
 * <p>
 * <b>Pre-condition</b>: A valid {@link Player} object must be passed to the constructor to initialize the panel.
 * <p>
 * <b>Post-condition</b>: The player panel is initialized with various components, including labels, buttons, and images,
 * to represent the player's current game status and interaction options.
 * <p>
 * <b>Invariant</b>: The panel displays dynamically updated player information such as available pawns, cards, and time.
 */
public class playerPanel extends JLayeredPane {
    Player plr;
    JPanel panel;
    public JLabel playerStats;
    public JPanel cardsPanel;
    JPanel score;
    public JPanel lastPlayedCardsPanel;
    public JLabel timerInstance;
    WallPaintingsWin win;
    public JLabel diskos;
    public JLabel ring;
    public JLabel kosmima;
    public JLabel ruto;
    JLabel knossosC;
    JLabel maliaC;
    JLabel phaistosC;
    JLabel zakrosC;

    /**
     * Constructs a new player panel for a given player. Initializes the panel layout,
     * sets up player statistics, cards, and interactive components like buttons.
     *
     * @param player The player whose information will be displayed on the panel.
     */
    public playerPanel(Player player) {
        win = new WallPaintingsWin(player.getFinds(),player);
        this.plr = player;
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

    /**
     * Sets the WallPaintingsWin object associated with the player panel.
     *
     * @param win The WallPaintingsWin object to set.
     */
    public void setWin(WallPaintingsWin win) {
        this.win = win;
    }

    /**
     * Gets the Zakros palace label.
     *
     * @return The Zakros palace label.
     */
    public JLabel getZakrosC() {
        return zakrosC;
    }

    /**
     * Gets the Phaistos palace label.
     *
     * @return The Phaistos palace label.
     */
    public JLabel getPhaistosC() {
        return phaistosC;
    }

    /**
     * Gets the Malia palace label.
     *
     * @return The Malia palace label.
     */
    public JLabel getMaliaC() {
        return maliaC;
    }

    /**
     * Gets the Knossos palace label.
     *
     * @return The Knossos palace label.
     */
    public JLabel getKnossosC() {
        return knossosC;
    }


}