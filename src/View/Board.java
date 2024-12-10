package src.View;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Board extends JFrame {
    /**
     * <b>Constructor </b>: Creates a new game window and initializes buttons, panels and the music of the game
     * <b>postconditions</b>: The window gets created before a new game starts or when the new game button is pressed.
     * @throws IOException
     * @throws LineUnavailableException
     */
    JPanel framePanel;
    JLabel timer;
    JPanel menuPanel;
    public JMenuBar menuBar;
    public JMenuItem newGame;
    public JMenuItem Save;
    public JMenuItem Continue;
    public JMenuItem Exit;
    public JMenuItem mute;
    public JLayeredPane Dashboard;
    public JButton cardStackBut;
    public JLabel stackInfo;
    public JPanel knossosPath;
    public JPanel maliaPath;
    public JPanel phaistosPath;
    public JPanel zakrosPath;
    public Board() {
        setTitle("Αναζητώντας τα χαμένα Μινωικά Ανάκτορα");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem cont = new JMenuItem("Continue Saved Game");
        JMenuItem mute = new JMenuItem("Mute Music");
        JMenuItem exit = new JMenuItem("Exit");
        menuBar.add(newGame);
        menuBar.add(save);
        menuBar.add(cont);
        menuBar.add(mute);
        menuBar.add(exit);
        JLabel bgImage = new JLabel(new ImageIcon("src/assets/images/background.jpg"));
        Dashboard = new JLayeredPane();
        bgImage.setBounds(0, 0, 1265, 538);
        Dashboard.add(bgImage, JLayeredPane.DEFAULT_LAYER);
        Dashboard.setLayout(null);
        Dashboard.setBounds(0,150,1265,538);
        ImageIcon originalIcon = new ImageIcon("src/assets/images/cards/backCard.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(90,118,Image.SCALE_SMOOTH);
        cardStackBut = new JButton(new ImageIcon(scaledImage));


        cardStackBut.setBounds(50, 338, 90, 118);
        Dashboard.add(cardStackBut, JLayeredPane.PALETTE_LAYER);

        stackInfo = new JLabel("<html> Available Cards: default<br> Check Points: default<br> Turn: default</html>", SwingConstants.LEFT);
        stackInfo.setLayout(null);
        stackInfo.setOpaque(true);
        stackInfo.setFont(new Font("Serif", Font.BOLD, 13));
        stackInfo.setBounds(15,465, 150,60);
        stackInfo.setBackground(Color.WHITE);
        stackInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Dashboard.add(stackInfo, JLayeredPane.PALETTE_LAYER);

        knossosPath = new JPanel(new GridLayout(1,9,0, 0));
        knossosPath.setOpaque(false);
        knossosPath.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        knossosPath.setBounds(340,50,750,100);

        maliaPath = new JPanel(new GridLayout(1,9,0, 0));
        maliaPath.setOpaque(false);
        maliaPath.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        maliaPath.setBounds(340,160,750,100);

        phaistosPath = new JPanel(new GridLayout(1,9,0, 0));
        phaistosPath.setOpaque(false);
        phaistosPath.setBorder(BorderFactory.createLineBorder(Color.BLACK,5 ));
        phaistosPath.setBounds(340,270,750,100);

        zakrosPath = new JPanel(new GridLayout(1,9,0, 0));
        zakrosPath.setOpaque(false);
        zakrosPath.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        zakrosPath.setBounds(340,380,750,100);


        Dashboard.add(knossosPath, JLayeredPane.PALETTE_LAYER);
        Dashboard.add(maliaPath, JLayeredPane.PALETTE_LAYER);
        Dashboard.add(phaistosPath, JLayeredPane.PALETTE_LAYER);
        Dashboard.add(zakrosPath, JLayeredPane.PALETTE_LAYER);
        add(Dashboard);
        setResizable(false);
    }
}
