package view;

import Phase1.model.palace;
import Phase1.model.positions.Path;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import Controller.Controller;

public class initializeBoard extends JFrame {
    private final Controller control;
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
    palace knossos;
    palace malia;
    palace zakros;
    palace phaistos;

    public initializeBoard(Controller controller) throws IOException, LineUnavailableException {
        this.control = controller;
        controller.playMusic();
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
        newGame.addActionListener(new ActionListener() {
            /**
             * <b>NEW GAME BUTTON</b>: When the button is pressed, the original window is disposed and a new one is created with the original game status, effectively starting a new game
             * @param ng the new game event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent ng) {
                try {
                    control.startNewGame();
                    control.stopMusic();
                    dispose();
                    new initializeBoard(new Controller());
                } catch (IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Save = new JMenuItem("Save Game");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent sg) {
                JOptionPane optionPane = new JOptionPane("The game normally would save here", JOptionPane.PLAIN_MESSAGE);
                JDialog dialog = optionPane.createDialog("PLACEHOLDER");
                dialog.setVisible(true);
            }
        });

        Continue = new JMenuItem("Continue Saved Game");
        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.Winner()){
                    System.exit(0);
                }

            }
        });
        Exit = new JMenuItem("Exit Game");
        Exit.addActionListener(new ActionListener() {
            /**
             * <b>Exit Game Button</b>: Exits the game when pressed
             * @param eg the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent eg) {
                System.exit(0);//Closes the game
            }
        });
        mute = new JMenuItem("Mute");
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!control.isPlaying()){
                    control.playMusic();
                    mute.setText("Mute");
                }
                else{
                    control.stopMusic();
                    mute.setText("Unmute");
                }
            }
        });
        menuBar.add(newGame);
        menuBar.add(Save);
        menuBar.add(Continue);
        menuBar.add(mute);
        menuBar.add(Exit);
        menuPanel.add(menuBar);

        Dashboard = new JLayeredPane();
        background = new JPanel(new BorderLayout());

        knossos = new palace("knossos", new Path("knossos"));
        malia = new palace("malia", new Path("malia"));
        zakros = new palace("zakros", new Path("zakros"));
        phaistos = new palace("phaistos", new Path("phaistos"));

        ImageIcon backgroundImage = new ImageIcon("Phase1/assets/images/background.jpg");
        background.add(new JLabel(backgroundImage));
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
        ArrayList<JButton> cards = new ArrayList<JButton>();
        for (int i=0;i<knossos.getNumCards().size(); i++){
            if(i%2 == 1){
                continue;
            }
            // Load the image from the card
            Image originalImage = knossos.getNumCards().get(i).getImage().getImage();

            Image scaledImage = originalImage.getScaledInstance(73, 90, Image.SCALE_SMOOTH);

            // Create an ImageIcon from the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Create a button with the scaled icon
            JButton button = new JButton(scaledIcon);
            button.setSize(73,90);
            // Add the button to the list
            cards.add(button);
        }
        for (int i=0; i< cards.size(); i++){
            cardButtosPane.add(cards.get(i));
        }
        player1Panel.add(cardButtosPane,FlowLayout.LEFT);
        framePanel.add(player1Pane);
        framePanel.add(Dashboard, BorderLayout.CENTER);
        add(framePanel);
        setResizable(false);
        setVisible(true);
        control.timer();

    }
}
