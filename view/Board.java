package view;

import Phase1.model.palace;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
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
    JMenuBar menuBar;
    JMenuItem newGame;
    JMenuItem Save;
    JMenuItem Continue;
    JMenuItem Exit;
    JMenuItem mute;
    JLayeredPane Dashboard;
    JPanel background;

    public Board() {
        setTitle("Αναζητώντας τα χαμένα Μινωικά Ανάκτορα");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);
        framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());
        menuPanel = new JPanel(new FlowLayout(getInsets().left));
        framePanel.add(menuPanel, BorderLayout.NORTH);
        framePanel.setBackground(Color.BLUE);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        newGame = new JMenuItem("New Game");
        Save = new JMenuItem("Save Game");
        Continue = new JMenuItem("Continue Saved Game");
        Exit = new JMenuItem("Exit Game");
        mute = new JMenuItem("Mute");
        menuBar.add(newGame);
        menuBar.add(Save);
        menuBar.add(Continue);
        menuBar.add(mute);
        menuBar.add(Exit);
        menuPanel.add(menuBar);

        Dashboard = new JLayeredPane();
        background = new JPanel(new BorderLayout());

        //ImageIcon backgroundImage = new ImageIcon("Phase1/assets/images/background.jpg");
        //background.add(new JLabel(backgroundImage));
        background.setBounds(0,130,1280,540);
        Dashboard.add(background,JLayeredPane.DEFAULT_LAYER);
        JLayeredPane player1Pane = new JLayeredPane();
        player1Pane.setBounds(0,30,1280,130);
        JPanel player1Panel = new JPanel();
        player1Panel.setBounds(0,0,1280,130);
        player1Pane.add(player1Panel,JLayeredPane.DEFAULT_LAYER);
        player1Panel.setBackground(Color.MAGENTA);

        JLayeredPane player2Pane = new JLayeredPane();
        player1Pane.setBounds(0,30,1280,130);
        JPanel cardButtosPane = new JPanel();
        cardButtosPane.setBounds(0,0,1280,130);

        player1Panel.add(cardButtosPane,FlowLayout.LEFT);
        framePanel.add(player1Pane);
        framePanel.add(Dashboard, BorderLayout.CENTER);
        add(framePanel);
        setResizable(false);
    }
}
