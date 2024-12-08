package Phase1;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class View extends JFrame {
    /**
     * <b>Constructor </b>: Creates a new game window and initializes buttons, panels and the music of the game
     * <b>postconditions</b>: The window gets created before a new game starts or when the new game button is pressed.
     * @throws IOException
     * @throws LineUnavailableException
     */
    public JPanel framePanel;
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

    public View() {
        setTitle("Αναζητώντας τα χαμένα Μινωικά Ανάκτορα");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,900);
        setLocationRelativeTo(null);

       /* JMenuBar menuBar = new JMenuBar();
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

        framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());

        JPanel Player1 = new JPanel();
        Player1.setBorder(new LineBorder(Color.RED,5));
        Player1.setBounds(0,0,1280,150);
        JPanel Player2 = new JPanel();
        Player2.setBorder(new LineBorder(Color.GREEN,5));
        Player2.setBounds(0,722,1280,150);
        add(Player1);
        add(Player2);*/
        //add(framePanel, BorderLayout.CENTER);



        setResizable(false);
    }
}
