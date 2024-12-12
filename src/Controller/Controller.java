package src.Controller;

import src.View.playerPanel;
import src.View.wallPaintingsWin;
import src.model.Player;
import src.model.cards.Card;
import src.model.findings.Finding;
import src.model.pawns.Arch;
import src.model.pawns.Pawn;
import src.model.pawns.Thesseus;
import src.model.positions.FindingPosition;
import src.model.positions.Path;
import src.model.positions.Position;
import src.View.Board;
import src.model.palace;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The src.Controller class manages the overall game logic, including player
 * initialization,
 * turn management, music playback, and victory conditions.
 */
public class Controller {
    /*
     * Game variables
     */
    Player player1;
    Player player2;
    palace knossos;
    palace malia;
    palace zakros;
    palace phaistos;
    private Stack<Card> cardStack = new Stack<Card>();
    private ArrayList<Finding> rareFindings = new ArrayList<>();
    public playerPanel pl1;
    public playerPanel pl2;
    private File Music;
    private Clip clip;
    private boolean isPlaying = true;
    Board board;
    int player = 0;
    int disposedCards =0;

    /**
     * Constructs a src.Controller instance and initializes the game state.
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: Players are initialized, and a starting player is selected.
     * <p>
     * Invariant: There are always two players in the game.
     */
    public Controller() {
        InitializePlayers();
        startingPlayer();
    }
    /**
     * Initializes the palaces with their respective names and paths.
     * This method sets up four palaces: Knossos, Malia, Zakros, and Phaistos.
     * Each palace is created with a name and a corresponding Path object.
     *<p>
     * <b>Pre-condition</b>: The necessary resources and environment for creating
     * Palace and Path objects must be available. No existing palaces should be
     * initialized to avoid overwriting.
     *<p>
     * <b>Post-condition</b>: Four Palace objects with the names Knossos, Malia,
     * Zakros, and Phaistos are initialized and associated with their respective
     * Path objects.
     *<p>
     * <b>Invariant</b>: The method maintains the integrity of the palace setup
     * by ensuring each Palace has a unique name and path. The method assumes a
     * state of uninitialized palaces before invocation.
     */
    public void initializePalaces(){
        knossos = new palace("knossos", new Path("knossos"));
        malia = new palace("malia", new Path("malia"));
        zakros = new palace("zakros", new Path("zakros"));
        phaistos = new palace("phaistos", new Path("phaistos"));
        rareFindings.add(new Finding(30,new ImageIcon("src/assets/images/findings/diskos.jpg"),"diskos"));
        rareFindings.add(new Finding(25,new ImageIcon("src/assets/images/findings/kosmima.jpg"),"kosmima"));
        rareFindings.add(new Finding(25,new ImageIcon("src/assets/images/findings/ring.jpg"),"ring"));
        rareFindings.add(new Finding(25,new ImageIcon("src/assets/images/findings/ruto.jpg"),"ruto"));

        Random rand = new Random();
        while(true)
        {
            try {
                FindingPosition position = (FindingPosition) phaistos.getPath().getPositions().get(rand.nextInt(8));
                if (position instanceof FindingPosition){
                    ((FindingPosition) position).setFind(rareFindings.get(0));
                    break;
                }
            } catch (ClassCastException ignored) {
            }
        }
        while(true)
        {
            try {
                FindingPosition position = (FindingPosition) knossos.getPath().getPositions().get(rand.nextInt(8));
                if (position instanceof FindingPosition){
                    ((FindingPosition) position).setFind(rareFindings.get(0));
                    break;
                }
            } catch (ClassCastException ignored) {
            }
        }
        while(true)
        {
            try {
                FindingPosition position = (FindingPosition) malia.getPath().getPositions().get(rand.nextInt(8));
                if (position instanceof FindingPosition){
                    ((FindingPosition) position).setFind(rareFindings.get(0));
                    break;
                }
            } catch (ClassCastException ignored) {
            }
        }
        while(true)
        {
            try {
                FindingPosition position = (FindingPosition) zakros.getPath().getPositions().get(rand.nextInt(8));
                if (position instanceof FindingPosition){
                    ((FindingPosition) position).setFind(rareFindings.get(0));
                    break;
                }
            } catch (ClassCastException ignored) {
            }
        }
        for (int i=0;i<9;i++){
            if (knossos.getPath().getPositions().get(i) instanceof FindingPosition) {
                System.out.println(((FindingPosition) knossos.getPath().getPositions().get(i)).getFind());
            }
        }
    }

    /**
     * Initializes the players for the game.
     *<p>
     * <b>Pre-condition</b>: None.
     * <p>
     * <b>Post-condition</b>: player1 and player2 are assigned new Player instances.
     * <p>
     * <b>Invariant</b>: Each player has their respective music file assigned.
     */
    private void InitializePlayers() {
        player1 = new Player(0, new ArrayList<Card>(), new ArrayList<Pawn>(),new File("src/assets/music/Player1.wav"), "1");
        player2 = new Player(0, new ArrayList<Card>(), new ArrayList<Pawn>(),new File("src/assets/music/Player2.wav"), "2");

        for (int i=0; i<3; i++){
            player1.getPawns().add(new Arch());
            player2.getPawns().add(new Arch());
        }
        player1.getPawns().add(new Thesseus());
        player2.getPawns().add(new Thesseus());
    }

    /**
     * Randomly selects the starting player and sets their music file.
     *<p>
     * <b>Pre-condition</b>: player1 and player2 must be initialized.
     * <p>
     * <b>Post-condition</b>: currentPlayer is set to either player1 or player2, and
     * their corresponding music file is assigned to Music.
     * <p>
     * <b>Invariant</b>: The starting player is always one of the two players.
     */
    private void startingPlayer() {
        Random rand = new Random();
        int turn;
        turn = rand.nextInt(2);
        if (turn == 0) {
            player = 1;
            Music = player1.getMusic();
        } else {
            player = 2;
            Music = player2.getMusic();
        }
    }

    /**
     * Plays the music associated with the current player in a continuous loop.
     *<p>
     * <b>Pre-condition</b>: Music must be assigned to a valid File object.
     * <p>
     * <b>Post-condition</b>: The music starts playing and loops continuously.
     * <p>
     * <b>Invariant</b>: Only one music file plays at any given time.
     */
    public void playMusic() {
        stopMusic();
        try {
            AudioInputStream m = AudioSystem.getAudioInputStream(Music);
            clip = AudioSystem.getClip();
            clip.open(m);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Stops the currently playing music
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: Music playback is stopped.
     * <p>
     * Invariant: The music file remains unchanged.
     */
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Checks if the music is currently playing.
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: The method returns true if the music is playing, false
     * otherwise.
     * <p>
     * Invariant: The music status is consistent with the clip's running state.
     *
     * @return true if the music is playing, false otherwise.
     */
    public boolean isPlaying() {
        return clip.isRunning();
    }

    /**
     * Switches the turn to the next player and updates the music accordingly.
     *<p>
     * Pre-condition: player must be assigned.
     * <p>
     * Post-condition: player is switched to the other player, and
     * their music starts playing.
     * <p>
     * Invariant: The turn alternates between player1 and player2.
     */
    public void changeTurn() {
        if (player == 1) {
            pl1.timerInstance.setText("Χρόνος: 30");
            player = 2;
            board.add(pl2);
            board.remove(pl1);
            Music = player2.getMusic();
        } else {
            player = 1;
            pl2.timerInstance.setText("Χρόνος: 30");
            board.add(pl1);
            board.remove(pl2);
            Music = player1.getMusic();
        }
        disposedCards=0;
        updateBoard();
        playMusic();
    }

    public void updateBoard() {
        if (player == 1) {
            SwingUtilities.invokeLater(() -> {
                board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +
                        "<br> Check Points: " + player1.getCheckPoints() +
                        "<br> Turn: " + player + "</html>");
            });
        }
        else{
            SwingUtilities.invokeLater(() -> {
                board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +
                        "<br> Check Points: " + player2.getCheckPoints() +
                        "<br> Turn: " + player + "</html>");
            });
        }
        board.revalidate();
        board.repaint();
    }

    /**
     * Moves a player's pawn based on the card's value and the specified path.
     *<p>
     * Pre-condition: The pawn, card, and path must be non-null and valid.
     * <p>
     * Post-condition: The pawn is moved along the specified path based on the
     * card's value.
     * <p>
     * Invariant: The pawn always remains on a valid path.
     *
     * @param pawn the player's pawn to move.
     * @param card the card determining the movement value.
     * @param path the path on which the pawn moves.
     */
    public void movePawn(Pawn pawn, Card card, Path path) {

    }

    /**
     * Runs a countdown timer for the player's turn. If time runs out, the turn
     * ends.
     *<p>
     * Pre-condition: The game must be running.
     * <p>
     * Post-condition: The current player's turn ends when the timer reaches 0.
     * <p>
     * Invariant: The timer always starts at 30 seconds for each turn.
     */
    public void timer() {
        while (isPlaying) {
            Clip clip;
            File countdown = new File("src/assets/music/countdown.wav");
            try {
                AudioInputStream m = AudioSystem.getAudioInputStream(countdown);
                clip = AudioSystem.getClip();
                clip.open(m);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int seconds = 30;
            for (int i = seconds; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                    break;
                }
                if (i <= 5) {
                    clip.start();
                }
                if (player ==1){
                    pl1.timerInstance.setText("Χρόνος: " + i);
                    updateBoard();
                }
                else{
                    pl2.timerInstance.setText("Χρόνος: " + i);
                    updateBoard();
                }
                System.out.println(i);
            }
            changeTurn();
            System.out.println("New Turn!");
            clip.stop();
        }
    }

    /**
     * Checks if either player has won the game. If so, stops the game.
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: The game stops if a player wins.
     * <p>
     * Invariant: Only one player can win the game at a time.
     *
     * @return true if the game is still running, false if there is a winner.
     */
    public boolean Winner() {
        //TODO: Implement win code.
        if (player1.getVictoryStatus()) {
            System.out.println("Player 1 wins!");
            isPlaying = !isPlaying;
        } else if (player2.getVictoryStatus()) {
            System.out.println("Player 2 wins!");
            isPlaying = !isPlaying;
        }
        return isPlaying;
    }

    /**
     * Checks if the game is still running.
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: Returns the current game status.
     * <p>
     * Invariant: The game state remains consistent with the players' statuses.
     *
     * @return true if the game is running, false otherwise.
     */
    public boolean isGameRunning() {
        return Winner();
    }


    /**
     * Initializes the cards stack from the cards of the palaces
     * <p>
     * It also initializes the Player cards from the stack.
     */
    private void initializeCards() {
        setCardListeners(knossos);
        setCardListeners(malia);
        setCardListeners(zakros);
        setCardListeners(phaistos);
        cardStack.addAll(knossos.getNumCards());
        cardStack.addAll(malia.getNumCards());
        cardStack.addAll(zakros.getNumCards());
        cardStack.addAll(phaistos.getNumCards());
        cardStack.addAll(knossos.getSpCards());
        cardStack.addAll(malia.getSpCards());
        cardStack.addAll(zakros.getSpCards());
        cardStack.addAll(phaistos.getSpCards());

        Collections.shuffle(cardStack);
        for (int i=0;i<8;i++){
            player1.getCards().add(cardStack.pop());

            player1.getCards().get(i).getCardButton().putClientProperty("player", player1); // Associate the card with player1

            player2.getCards().add(cardStack.pop());
            player2.getCards().get(i).getCardButton().putClientProperty("player", player2);
        }
        System.out.println(cardStack.size());
        System.out.println("Player 1 cards: ");
        for (int i=0; i<8; i++){
            System.out.println(player1.getCards().get(i).toString());
        }
    }

    /**
     * Initializes the game board by setting up necessary prerequisites for the game to begin.
     * <p>
     * Post-conditions:
     * <p>
     * - The game state is changed to active.
     * <p>
     * - The game board is initialized with players and palaces.
     * <p>
     * - The player who goes first in the game is selected.
     * <p>
     * - Music associated with the game starts playing.
     */
    public void initializeBoard() {
        board = new Board();
        board.setLayout(null);
        initializePalaces();
        InitializePlayers();
        startingPlayer();
        initializeCards();
        pl1 = new playerPanel(player1);
        pl2 = new playerPanel(player2);
        pl1.setBounds(0,0,pl1.getWidth()-15,pl1.getHeight());
        pl2.setBounds(0,688,pl2.getWidth()-15, pl2.getHeight());
        for (int i=0; i<8; i++){
            pl1.cardsPanel.add(player1.getCards().get(i).getCardButton());
            pl2.cardsPanel.add(player2.getCards().get(i).getCardButton());
        }

        board.add(pl1);
        board.add(pl2);
        JPanel cover = new JPanel();
        cover.setBounds(0,0,1280,150);
        cover.setLayout(null);
        cover.setBackground(Color.GRAY);
//        for (int i=0; i<8; i++)
//        {
//            board.knossosPath.add(new JLabel(new ImageIcon("src/assets/images/paths/knossos.jpg")));
//        }
//        if (player == 1){
//            board.remove(pl2);
//            board.add(pl1);
//        }
//        else{
//            board.remove(pl1);
//            board.add(pl2);
//        }
        isPlaying = true;
        playMusic();
        if (player ==1){
            board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +"<br> Check Points: "+ player1.getCheckPoints() +"<br> Turn: "+player+"</html>");
        }
        else{
            board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +"<br> Check Points: "+ player2.getCheckPoints() +"<br> Turn: "+player+"</html>");

        }

        board.revalidate();
        board.repaint();
    }
    /**
     * Starts a new game by" Total cards in stack: " +  reinitializing players, selecting the starting player
     *<p>
     * Pre-condition: None.
     * <p>
     * Post-condition: The game is reset, and a new game starts.
     * <p>
     * Invariant: The game always starts with two players.
     */
    public void startNewGame() throws LineUnavailableException, IOException {
        initializeBoard();
        board.setVisible(true);
        System.out.println("New Game Started");
        timer();
    }

    /**
     * Determines if the current card allows a pawn to move based on the game rules.
     *<p>
     * <b>Pre-condition</b>: Both current card (c1) and previous card (c2) must not
     * be null. The game should be in progress, with valid cards being used.
     *<p>
     * <b>Post-condition</b>: The method determines whether the pawn can move according
     * to the current and previous cards, updating any applicable game state based on
     * the card rules.
     *<p>
     * <b>Invariant</b>: The method does not modify the cards themselves but only
     * evaluates the conditions for movement. The game state invariant of turn order
     * remains consistent.
     *
     * @param c1 the current card being played.
     * @param c2 the previous card played.
     * @return true if the conditions for moving the pawn are met; false otherwise.
     */
    public boolean matchCard(Card c1, Card c2) {
        //TODO: Implement logic that determines card type and the corresponding action.
        return false;
    }

    /**
     * Executes the action of throwing the specified card.
     *<p>
     * <b>Pre-condition</b>: The card (c1) must be a valid, non-null instance
     * of the Card class that belongs to the current player's hand.
     *<p>
     * <b>Post-condition</b>: The specified card (c1) is removed from the player's
     * hand and any game-specific effects associated with throwing the card are
     * triggered.
     *<p>
     * <b>Invariant</b>: The method does not alter the fundamental state of the game
     * other than the removal of the card from the player's hand and triggering the
     * card's effects. The game maintains a valid state before and after the method
     * execution.
     *
     * @param c1 the card to be thrown.
     * @param pl the player playing.
     * @param pl2 the opponent player that will have his card checked.
     */
    public void throwCard(Card c1, Player pl, Player pl2){
        //TODO: Build game mechanic. Method is currently a placeholder and the parameters could be altered.
        if(c1 != null){
            if(matchCard(c1, pl2.getCards().get(0))){
                pl.getCards().remove(c1);
                pl.getCards().add(cardStack.pop());
            }
        }
    }
    public void pullCard(Player player, JPanel panel) {

        if (player.getCards().size() == 8) {
            System.out.println("Player has max cards");
        }
        else if (disposedCards > 0) {
            System.out.println("Player has already disposed a card");
        }
        else if (cardStack.isEmpty()){
            System.out.println("No more available cards");
        }
        else {
            player.getCards().add(cardStack.pop());
            int lastCardIndex = player.getCards().size() - 1;
            Card newCard = player.getCards().get(lastCardIndex);
            newCard.getCardButton().putClientProperty("player", player);
            panel.add(newCard.getCardButton());
            disposedCards++;
            panel.revalidate();
            panel.repaint();
            updateBoard();
        }
    }

    public void disposeCard(Card c1, Player player, JPanel panel) {
        if (disposedCards == 1){
            System.out.println("Player has already disposed a card");
        }
        else if (player.getCards().size() < 8)
        {
            System.out.println("Less than 8 cards");
            return;
        }
        else
        {
            for (int i = 0; i < player.getCards().size(); i++) {
                if (player.getCards().get(i) == c1) {
                    // Remove card from player's list
                    player.getCards().remove(i);
                    System.out.println("Card Disposed");

                    // Find and remove the corresponding JButton from the panel
                    for (Component component : panel.getComponents()) {
                        if (component instanceof JButton) {
                            JButton button = (JButton) component;
                            Card card = (Card) button.getClientProperty("card");
                            if (card == c1) {
                                panel.remove(button);  // Remove the button from the panel
                                break;
                            }
                        }
                    }

                    // Revalidate and repaint the panel to reflect the changes
                    panel.revalidate();
                    panel.repaint();
                    return;  // Exit after removing the card
                }
            }
        }
    }

    /**
     * Code is inspired from the MVC_CardGame we were provided as an example.
     */

    /**
     * CardListener is a private inner class that implements MouseListener to handle mouse events
     * for the board application. It listens to mouse interactions specifically on button components
     * and triggers the corresponding actions when the game is running.
     *
     * Overrides methods from MouseListener to handle mouse events such as click, press, release,
     * enter, and exit.
     */
    private class CardListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(e.getSource() instanceof JButton)) {
                return;
            }
            JButton button = (JButton) e.getSource();
            Player player = (Player) button.getClientProperty("player");
            JPanel panel = (JPanel) button.getParent();
            Card card = (Card) button.getClientProperty("card");

            if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                System.out.println("left click");
            } else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                disposeCard(card, player, panel);
                board.repaint();
            }
            // Consume the event to prevent propagation
            e.consume();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!(e.getSource() instanceof JButton)) {
                return;
            }
            JButton button = (JButton) e.getSource();
            Player player = (Player) button.getClientProperty("player");
            JPanel panel = (JPanel) button.getParent();
            Card card = (Card) button.getClientProperty("card");

            if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                System.out.println("left click");
            } else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                disposeCard(card, player, panel);
                board.repaint();
            }

            // Consume the event to prevent propagation
            e.consume();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class StackListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(e.getSource() instanceof JButton)) {
                System.out.println("Not a button");
            } else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                JButton button = (JButton) e.getSource();
                if (player ==1){
                    pullCard(player1, pl1.cardsPanel);
                }
                else{
                    pullCard(player2, pl2.cardsPanel);
                }
                board.repaint();
                e.consume();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * Attaches mouse listeners to each card in the specified palace.
     *
     * <b>Pre-condition</b>: The palace must be initialized with both regular cards
     * and special cards, and each card must be capable of having a MouseListener
     * attached.
     *<p>
     * <b>Post-condition</b>: Each regular and special card in the specified palace
     * has a `CardListener` attached, facilitating mouse interaction.
     *<p>
     * <b>Invariant</b>: The total number of listeners corresponds with the total
     * number of regular and special cards in the palace, ensuring each card
     * is consistently managed.
     *
     * @param Palace the palace containing the cards to which listeners are to be attached
     */
    public void setCardListeners(palace Palace) {
        for (int i = 0; i < Palace.getNumCards().size(); i++) {
            Palace.getNumCards().get(i).getCardButton().addMouseListener(new CardListener());
            Palace.getNumCards().get(i).getCardButton().putClientProperty("card", Palace.getNumCards().get(i)); // Associate the card with the button

        }
        for (int i = 0; i<Palace.getSpCards().size(); i++){
            Palace.getSpCards().get(i).getCardButton().addMouseListener(new CardListener());
            Palace.getSpCards().get(i).getCardButton().putClientProperty("card", Palace.getSpCards().get(i)); // Associate the card with the button
        }

        board.cardStackBut.addMouseListener(new StackListener());
    }

    /**
     * Checks if the given position contains a finding and returns it.
     * <p>
     * <b>Pre-condition</b>: The position provided must not be null. It is expected to be
     * an instance of FindingPosition for any findings to be retrieved.
     * <p>
     * <b>Post-condition</b>: Returns the Finding associated with the position, or null
     * if the position does not contain a finding or is not an instance of FindingPosition.
     * <p>
     * <b>Invariant</b>: The position's state remains unchanged by this method.
     *
     * @param p the position to check for a finding. It should ideally be an instance of FindingPosition.
     * @return the finding associated with the position if it exists; null otherwise.
     */
    public Finding hasFinding(Position p){
        if (p instanceof FindingPosition){
            Finding posFind = ((FindingPosition) p).getFind();
            ((FindingPosition) p).setFind(null);
            return posFind;
        }
        else return null;
    }

    /**
     * The main method serves as the entry point for the application, initializing
     * and starting a new game session.
     *<p>
     * <b>Pre-condition</b>: The system must support audio playback, and all necessary
     * game resources (such as audio files) must be available and correctly formatted.
     *<p>
     * <b>Post-condition</b>: A new game session is initialized and started. The application
     * enters its main loop, handling user input and game events.
     *<p>
     * <b>Invariant</b>: The main method continually maintains control of the application flow
     * until termination, ensuring that resources such as audio lines are properly managed
     * and released. Command-line arguments are not utilized.
     *
     * @param args command-line arguments (not used).
     * @throws UnsupportedAudioFileException if there is a problem with the audio file format.
     * @throws LineUnavailableException if the audio line cannot be opened.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Controller controller = new Controller();
        controller.startNewGame();
    }
}