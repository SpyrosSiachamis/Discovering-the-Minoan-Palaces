package src.View;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;

public class Board extends JFrame {
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
        setResizable(false);
    }
}
