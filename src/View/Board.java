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
    public JMenuItem mute = new JMenuItem("Mute Music");;
    public JLayeredPane Dashboard;
    public JButton cardStackBut;
    public JLabel stackInfo;
    public JPanel knossosPath;
    public JPanel maliaPath;
    public JPanel phaistosPath;
    public JPanel zakrosPath;
    public JLayeredPane layeredPathPane = new JLayeredPane();
    public JPanel paths = new JPanel(new GridBagLayout());
    JPanel player1Pawns = new JPanel(null);
    JPanel player2Pawns = new JPanel(null);
    public GridBagConstraints gbc = new GridBagConstraints();
    public Board() {
        ImageIcon gameIcon = new ImageIcon("src/assets/images/gameIcon.jpg");
        setIconImage(gameIcon.getImage());

        setTitle("Αναζητώντας τα χαμένα Μινωικά Ανάκτορα");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1265,885);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem cont = new JMenuItem("Continue Saved Game");

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting Game");
                System.exit(0);
            }
        });
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
        JPanel pathInfo = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.3;
        c.weighty = 1.3;
        pathInfo.setOpaque(false);
        pathInfo.setBounds(330, 20, 900, 40);

        JLabel m20 = new JLabel("-20 points  ");
        m20.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(m20, c);
        JLabel m15 = new JLabel("-15 points  ");
        m15.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(m15, c);
        JLabel m10 = new JLabel("-10 points  ");
        m10.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(m10,c);
        JLabel p5 = new JLabel("5 points  ");
        p5.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(p5,c);
        JLabel p10 = new JLabel("10 points  ");
        p10.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(p10,c);
        JLabel p15= new JLabel("15 points  ");
        p15.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(p15,c);
        JLabel p30= new JLabel("<html>30 points<br>Check Point!</html>  ");
        p30.setFont(new Font("Serif", Font.BOLD, 13));
        pathInfo.add(p30,c);
        JLabel p35 = new JLabel("35 points  ");
        p35.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(p35,c);
        JLabel p50= new JLabel("50 points  ");
        p50.setFont(new Font("Serif", Font.BOLD, 17));
        pathInfo.add(p50,c);
        layeredPathPane.setBounds(330,50,930,440);
        player1Pawns.setBounds(0,0,930,440);
        player1Pawns.setOpaque(false);
        player2Pawns.setOpaque(false);
        player2Pawns.setBounds(0,0,930,440);
        paths.setOpaque(false);
        paths.setBounds(0,0,930,440);
        layeredPathPane.add(paths,JLayeredPane.DEFAULT_LAYER);
        layeredPathPane.add(player1Pawns, JLayeredPane.PALETTE_LAYER);
        layeredPathPane.add(player2Pawns, JLayeredPane.MODAL_LAYER);
        Dashboard.add(layeredPathPane, JLayeredPane.PALETTE_LAYER);
        Dashboard.add(pathInfo, JLayeredPane.PALETTE_LAYER);
        add(Dashboard);
        setResizable(false);
    }

    public JPanel getPlayer2Pawns() {
        return player2Pawns;
    }

    public void setPlayer2Pawns(JPanel player2Pawns) {
        this.player2Pawns = player2Pawns;
    }

    public JPanel getPlayer1Pawns() {
        return player1Pawns;
    }

    public void setPlayer1Pawns(JPanel player1Pawns) {
        this.player1Pawns = player1Pawns;
    }
}
