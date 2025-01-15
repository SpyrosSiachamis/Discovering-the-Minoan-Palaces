package src.Controller;
import src.View.*;
import src.model.Exceptions.FailedToAddFindingException;
import src.model.Player;
import src.model.cards.*;
import src.model.findings.Finding;
import src.model.findings.Fresco;
import src.model.findings.RareFinding;
import src.model.pawns.Arch;
import src.model.pawns.Pawn;
import src.model.pawns.Thesseus;
import src.model.positions.*;
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
 * The {@code Controller} class manages the overall game logic, including player
 * initialization, turn management, music playback, and victory conditions.
 * <p>
 * The class is responsible for initializing the game state, switching between players' turns,
 * handling the music playback associated with each player, and checking for victory conditions.
 * It maintains the game's flow and ensures that the game progresses through its stages.
 * <p>
 * Key responsibilities of this class:
 * <ul>
 *     <li>Initializing players with their respective attributes, such as cards and pawns.</li>
 *     <li>Managing player turns and ensuring that the game alternates between players correctly.</li>
 *     <li>Handling the start and stop of music corresponding to the active player.</li>
 *     <li>Tracking and updating the game board with necessary changes (e.g., card stack, score, statues).</li>
 *     <li>Managing victory conditions and ending the game when appropriate.</li>
 *     <li>It also manages the connection between the Model and the View classes, according to the MVC model of the project and manages the changes between them.</li>
 * </ul>
 * <p>
 * <b>Invariant</b>: The game will always have two players initialized, and the game state will
 * always progress through a series of turns until the game ends.
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
    private ArrayList<Finding> findings = new ArrayList<>();
    private ArrayList<Fresco> wallPaintings = new ArrayList<>();
    public playerPanel pl1;
    public playerPanel pl2;
    private File Music;
    private Clip clip;
    Board board;
    Player player = null;
    int disposedCards =0;
    boolean thrownCard = false;
    Position[][] positions1 = new Position[4][9];
    Position[][] positions2 = new Position[4][9];
    int originalSize;
    /**
     * Constructs a new {@code Controller} instance and initializes the game state.
     * This includes initializing the players and selecting the starting player.
     * <p>
     * <b>Pre-condition</b>:
     * - No pre-existing state requirements for this method.
     * <p>
     * <b>Post-condition</b>:
     * - Two players (player1 and player2) are initialized with their respective cards and pawns.
     * - A starting player is selected, determining who will take the first turn.
     * <p>
     * <b>Invariant</b>:
     * - The game will always have two players initialized, and a starting player will be selected after the constructor runs.
     */
    public Controller() {
        InitializePlayers();
        startingPlayer();
    }
    /**
     * Initializes the palaces and their respective paths, assigns findings, and sets up rare and statue findings at random positions and Fresco findings.
     * This method configures four palaces: Knossos, Malia, Zakros, and Phaistos, each with its own path and associated resources.
     * It also adds rare and statue findings at specific positions within the paths.
     * <p>
     * <b>Pre-condition</b>:
     * - The necessary resources (e.g., palace, path, findings, and fresco images) must be available.
     * - No existing palaces should be initialized to prevent overwriting.
     * <p>
     * <b>Post-condition</b>:
     * - Four Palace objects (Knossos, Malia, Zakros, and Phaistos) are initialized, each with a corresponding Path object.
     * - Rare findings are assigned to random positions within the paths of the palaces.
     * - Statue findings (e.g., snakes) are placed randomly within the paths.
     * - Fresco findings are placed in available positions within the paths of the palaces.
     * <p>
     * <b>Invariant</b>:
     * - Each palace must have a unique name and path.
     * - Findings, frescoes, and statues are randomly assigned to available positions within the paths.
     * - The integrity of the palace setup is maintained, and no palace should have overlapping or conflicting findings.
     * <p>
     * <b>Notes</b>:
     * - If the maximum number of attempts to place statue findings is reached, a warning is logged.
     * - The method assumes no existing findings or frescoes are already placed within the palaces.
     */
    public void initializePalaces() throws FailedToAddFindingException {

        knossos = new palace("knossos", new Path("knossos"));
        malia = new palace("malia", new Path("malia"));
        zakros = new palace("zakros", new Path("zakros"));
        phaistos = new palace("phaistos", new Path("phaistos"));
        for (int i = 0; i < 10; i++) {
            findings.add(new Finding(0, new ImageIcon("src/assets/images/findings/snakes.jpg"), "snakes"));
        }
        wallPaintings.add(new Fresco(20, new ImageIcon("src/assets/images/findings/fresco2_20.jpg"), "fresco2_20"));
        wallPaintings.add(new Fresco(20, new ImageIcon("src/assets/images/findings/fresco1_20.jpg"), "fresco1_20"));
        wallPaintings.add(new Fresco(15, new ImageIcon("src/assets/images/findings/fresco5_15.jpg"), "fresco5_15"));
        wallPaintings.add(new Fresco(15, new ImageIcon("src/assets/images/findings/fresco3_15.jpg"), "fresco3_15"));
        wallPaintings.add(new Fresco(15, new ImageIcon("src/assets/images/findings/fresco6_15.jpg"), "fresco6_15"));
        wallPaintings.add(new Fresco(20, new ImageIcon("src/assets/images/findings/fresco6_15.jpg"), "fresco4_20"));
        setPathListeners(knossos);
        setPathListeners(malia);
        setPathListeners(zakros);
        setPathListeners(phaistos);
        RareFinding[] rare = {new RareFinding(35, new ImageIcon("src/assets/images/findings/diskos.jpg"), "diskos"), new RareFinding(25, new ImageIcon("src/assets/images/findings/ring.jpg"), "ring"), new RareFinding(25, new ImageIcon("src/assets/images/findings/kosmima.jpg"), "kosmima"), new RareFinding(25, new ImageIcon("src/assets/images/findings/ruto.jpg"), "ruto")};
        palace[] palaces = {knossos, malia, zakros, phaistos};
        Random rand = new Random();

        //Place Rare Findings
        for (palace Palace : palaces) {
            int pos = -1;
            if (Palace.getName().equals("knossos")) {
                while (pos % 2 != 1) {
                    pos = rand.nextInt(9);
                    if (pos == 8) {
                        break;
                    }
                }
                FindingPosition rarePos = (FindingPosition) knossos.getPath().getPositions().get(pos);
                rarePos.setFind(rare[1]);
                System.out.println("Knossos Rare Finding Position: " + pos);
            } else if (Palace.getName().equals("malia")) {
                while (pos % 2 != 1) {
                    pos = rand.nextInt(9);
                    if (pos == 8) {
                        break;
                    }
                }
                FindingPosition rarePos = (FindingPosition) malia.getPath().getPositions().get(pos);
                rarePos.setFind(rare[2]);
                System.out.println("Malia Rare Finding Position: " + pos);
            } else if (Palace.getName().equals("phaistos")) {
                while (pos % 2 != 1 && pos != 8) {
                    pos = rand.nextInt(9);
                }
                FindingPosition rarePos = (FindingPosition) phaistos.getPath().getPositions().get(pos);
                rarePos.setFind(rare[0]);
                System.out.println("Phaistos Rare Finding Position: " + pos);
            } else if (Palace.getName().equals("zakros")) {
                while (pos % 2 != 1 && pos != 8) {
                    pos = rand.nextInt(9);
                }
                FindingPosition rarePos = (FindingPosition) zakros.getPath().getPositions().get(pos);
                rarePos.setFind(rare[3]);
                System.out.println("Zakros Rare Finding Position: " + pos);
            }
        }

        //Set statue findings
        int amountSnakes = 0;
        Random palaceRand = new Random();
        Finding snakes = new Finding(0, new ImageIcon("src/assets/images/findings/snakes.jpg"), "snakes");
        FindingPosition snakePos;
        while (amountSnakes < 10) {
            palace Palace = palaces[palaceRand.nextInt(4)];
            int pos = -1;
            boolean positionFound = false;
            int attempts = 0;
            while (!positionFound && attempts < 50) {
                pos = rand.nextInt(9);
                if (pos % 2 == 1 && pos != 8) {
                    snakePos = (FindingPosition) Palace.getPath().getPositions().get(pos);
                    if (snakePos.getFind() == null) {
                        snakePos.setFind(snakes);
                        amountSnakes++;
                        System.out.println(Palace.getName() + " Snake position: " + pos);
                        positionFound = true;
                        System.out.println("Snakes: " + amountSnakes);
                    }
                }
                attempts++;
            }
            if (attempts >= 50) {
                System.out.println("Warning: Max attempts reached for placing snakes in " + Palace.getName());
            }
        }
        Random randomFresco = new Random();
        int amountofFresco = 0;
        for (palace Palace : palaces) {
            if (amountofFresco == 6) {
                break;
            }
            for (int i=0; i<9; i++) {
                Position pos = Palace.getPath().getPositions().get(i);
                if (pos instanceof FindingPosition) {
                    if (((FindingPosition) pos).getFind() == null) {
                        int position = randomFresco.nextInt(wallPaintings.size());
                        ((FindingPosition) pos).setFind(wallPaintings.get(position));
                        wallPaintings.remove(position);
                        amountofFresco++;
                    }
                    if (amountofFresco == 6) {
                        break;
                    }
                }
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

    }

    /**
     * <b>WARNING: HAS BEEN CURRENTLY SET TO PLAYER 1 AS RANDOMZING THE PLAYER CAUSES ISSUES, MIGHT FIX AS A HOBBY AFTER THE PROJECT DEADLINE IS OVER</b>
     * <p>
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
        turn = 0;
        if (turn == 0) {
            player = player1;
            Music = player.getMusic();
        } else if (turn == 1) {
            player = player2;
            Music = player.getMusic();
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
     * Stops the currently playing music.
     * This method halts the playback of the music if it is currently running.
     * <p><b>Pre-condition:</b></p>
     * - There must be a valid music clip that is currently playing.
     * <p><b>Post-condition:</b></p>
     * - Music playback is stopped if it was playing.
     * <p><b>Invariant:</b></p>
     * - The music file remains unchanged and can be played again later if desired.
     * @throws IllegalStateException if the music clip is in an invalid state to stop.
     */
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Switches the turn to the next player and updates the music accordingly.
     * This method also handles stopping the current player's timer, hiding and showing pawns based on power activation,
     * and updating the board UI to reflect the new player's turn.
     * <p><b>Pre-condition:</b></p>
     * - The current player must be assigned.
     * - The game must have at least two players.
     * <p><b>Post-condition:</b></p>
     * - The turn is switched to the other player.
     * - The current player's pawns that do not have had their corresponding power activated are hidden, while the next player's pawns are shown.
     * - The board UI is updated to reflect the new player's turn.
     * - The music for the new player starts playing.
     * - The timer is reset for the new player's turn.
     * <p><b>Details:</b></p>
     * - If the card stack is empty, the game ends by calling the `endGame` method.
     * - The board UI is updated by removing the previous player panel and adding the new player's panel.
     * - Player-specific pawns are hidden or shown based on whether their powers are activated.
     * - The music associated with the new player starts playing, and if music is already playing, it is stopped and restarted.
     * - Player data is printed to the console for tracking.
     * - The UI is refreshed to reflect the new turn, and the updated player information is displayed.
     * <p><b>Invariant:</b></p>
     * - The turn alternates between Player 1 and Player 2.
     * - The music always corresponds to the current player's music.
     * - The timer is reset for the new player at the beginning of their turn.
     * @throws IOException if an error occurs while playing music.
     */
    public void changeTurn() {
        if (cardStack.isEmpty()) {
            endGame();
        }
        if (player1.getCheckPoints() + player2.getCheckPoints() >=4) {
            endGame();
        }
        if (player.getName().equals("1")) {
            pl1.timerInstance.setText("Χρόνος: 30");
            stopTimer();
            timer();
            board.remove(pl1);
            for (int i=0; i<player1.getPawns().size(); i++) {
                if (player1.getPawns().get(i).isPowerActivated()) {}
                else {
                    player1.getPawns().get(i).hidePawn();
                }
            }
            for (int i=0; i<player2.getPawns().size(); i++) {
                if (player2.getPawns().get(i).isPowerActivated()) {}
                else{
                    player2.getPawns().get(i).showPawn();
                }
            }
            board.add(pl2);
            player = player2;
            Music = player2.getMusic();
            System.out.println("Player 1 Points: " + player1.getPoints());
            System.out.println("Player 1 Statues: " + player1.getAmountOfStatues());
        } else {
            pl2.timerInstance.setText("Χρόνος: 30");
            stopTimer();
            timer();
            board.remove(pl2);
            for (int i=0; i<player2.getPawns().size(); i++) {
                if (player2.getPawns().get(i).isPowerActivated()) {}
                else {
                    player2.getPawns().get(i).hidePawn();
                }
            }
            for (int i=0; i<player1.getPawns().size(); i++) {
                if (player1.getPawns().get(i).isPowerActivated()) {}
                else {
                    player1.getPawns().get(i).showPawn();
                }
            }
            board.add(pl1);
            player = player1;
            Music = player1.getMusic();
            System.out.println("Player 2 Points: " + player2.getPoints());
            System.out.println("Player 2 Statues: " + player2.getAmountOfStatues());
        }

        disposedCards = 0;
        thrownCard = false;
        updateBoard();

        // Handle music playback
        if (!clip.isRunning()) {
            System.out.println("Skipping");
        } else {
            playMusic();
        }

        // Refresh the UI to reflect changes
        board.layeredPathPane.revalidate();
        board.layeredPathPane.repaint();
        System.out.println(player1.getName() + player1.getCards().size());
        System.out.println(player2.getName() + player2.getCards().size());
    }

    /**
     * Updates the game board to reflect the current state of the game, including information about the available cards,
     * the players' checkpoints, points, statues, and whose turn it is.
     * This method is responsible for updating the display for both players after every action.
     * <p><b>Pre-condition</b>:
     * - The game board, including the UI components such as the stack info, is already initialized and visible.
     * - The players' data (checkpoints, points, statues) and the current game state are updated.
     * </p>
     * <p><b>Post-conditions</b>:
     * - The board's stack info label is updated with the following data:
     *   - The number of available cards in the card stack.
     *   - The current player's checkpoints, points, and statues.
     *   - The name of the current player whose turn it is.
     * </p>
     * <p><b>Details</b>:
     * - Depending on which player is active (Player 1 or Player 2), the corresponding information is displayed.
     * - The information is formatted in HTML for better readability within the label.
     * - The UI updates are done using SwingUtilities.invokeLater to ensure thread safety when updating the UI.
     * - After updating the information, the board is revalidated and repainted to ensure the display is refreshed.
     * <p><b>Invariant</b>:
     * - The board's display always accurately reflects the state of the game.
     * - The player data is correctly shown for the active player.
     * - The UI is refreshed to show updated information on each turn.
     * </p>
     */
    public void updateBoard() {
        if (player.getName().equals("1")) {
            SwingUtilities.invokeLater(() -> {
                board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +
                        "<br> Check Points: " + player1.getCheckPoints() + " Points: " + player1.getPoints() +
                        "<br>Statues: "+ player1.getAmountOfStatues() + "<br>Turn: " + player.getName() + "</html>");
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +
                        "<br> Check Points: " + player2.getCheckPoints() + " Points: " + player2.getPoints() +
                        "<br>Statues: "+ player2.getAmountOfStatues() + "<br>Turn: " + player.getName() + "</html>");
            });
        }
        board.revalidate();
        board.repaint();
        pl1.setWin(new WallPaintingsWin(player1.getFinds(), player1));
        pl2.setWin(new WallPaintingsWin(player2.getFinds(), player2));
    }

    /**
     * Initializes the card stack from the cards of the palaces and assigns cards to the players.
     * This method adds all the cards from the four palaces (Knossos, Malia, Zakros, and Phaistos) to the card stack.
     * After adding the cards, the stack is shuffled, and each player is assigned a set of 8 cards.
     * The method also sets listeners for each card, as well as assigning player information to each card button.
     * <p><b>Pre-condition</b>:
     * - The palaces (Knossos, Malia, Zakros, and Phaistos) are initialized with the necessary cards.
     * </p>
     * <p><b>Post-conditions</b>:
     * - The card stack is populated with all the cards from the four palaces (both regular and special cards).
     * - The card stack is shuffled.
     * - Each player is assigned 8 cards, and these cards are added to their respective hand.
     * - The card buttons are updated with the player reference to ensure that the correct player owns the card.
     * </p>
     * <p><b>Invariant</b>:
     * - The card stack is shuffled to randomize the order.
     * - Each player has 8 cards after initialization.
     * - Each card button has a reference to its owning player.
     * </p>
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
        originalSize = cardStack.size();
        for (int i=0;i<8;i++){
            player1.getCards().add(cardStack.pop());
            player1.getCards().get(i).getCardButton().putClientProperty("player", player1);
        }
        for (int i=0;i<8;i++){
            player2.getCards().add(cardStack.pop());
            player2.getCards().get(i).getCardButton().putClientProperty("player", player2);
        }
    }

    /**
     * Initializes the game board by setting up all the necessary prerequisites for the game to begin.
     * This includes setting up the board layout, initializing the palaces, players, cards, and ensuring
     * the game is ready to be played. It also selects the starting player and begins the associated game music.
     * It also begins the first timer for the starting player.
     * <p><b>Pre-condition</b>:
     * - The game has not yet started.
     * - The players and palaces are properly initialized before calling this method.
     * </p>
     * <p><b>Post-conditions</b>:
     * - The game state is changed to active.
     * - The game board is initialized with players, paths, and positions corresponding to different palaces.
     * - The player who goes first in the game is selected and displayed on the board.
     * - Music associated with the game starts playing.
     * - The game UI components are adjusted, including player panels and their associated cards.
     * </p>
     * <p><b>Invariant</b>:
     * - The board layout remains consistent throughout the game.
     * - The current player is properly displayed based on the turn.
     * - The music is either playing or muted based on the player's interaction.
     * </p>
     * @throws FailedToAddFindingException if there is an issue during the initialization of a finding (e.g., adding a finding).
     */
    public void initializeBoard() throws FailedToAddFindingException {
        board = new Board();
        board.setLayout(null);
        initializePalaces();
        InitializePlayers();
        initializeCards();
        pl1 = new playerPanel(player1);
        pl2 = new playerPanel(player2);
        timer();
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
        board.gbc.weightx = 1.0;
        board.gbc.weighty = 1.0;
        for (int i=0;i<4;i++){
            for (int j=0;j<9;j++){
                if (i == 0){
                    positions1[i][j] = knossos.getPath().getPositions().get(j);
                    positions2[i][j] = knossos.getPath().getPositions().get(j);
                }
                else if (i == 1){
                    positions1[i][j] = malia.getPath().getPositions().get(j);
                    positions2[i][j] = malia.getPath().getPositions().get(j);
                }

                else if (i == 2){
                    positions1[i][j] = phaistos.getPath().getPositions().get(j);
                    positions2[i][j] = phaistos.getPath().getPositions().get(j);
                }
                else if (i == 3){
                    positions1[i][j] = zakros.getPath().getPositions().get(j);
                    positions2[i][j] = zakros.getPath().getPositions().get(j);
                }
            }
        }

        for(int r = 0; r<4; r++) {
            for (int c = 0; c < 9; c++) {
                board.gbc.gridx = c; // Column index
                board.gbc.gridy = r; // Row index
                JLabel label = positions1[r][c].getSquare().getSquareLabel();
                board.paths.add(label, board.gbc);
            }
        }
        if (player.getName().equals("1")){
            board.add(pl1);
            board.remove(pl2);
        }
        else{
            board.remove(pl1);
            board.add(pl2);
        }
        if (player.getName().equals("1")){
            board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +"<br> Check Points: "+ player1.getCheckPoints() +"<br> Turn: "+player.getName()+"</html>");
        }
        else{
            board.stackInfo.setText("<html> Available Cards: " + cardStack.size() +"<br> Check Points: "+ player2.getCheckPoints() +"<br> Turn: "+player.getName()+"</html>");
        }
        board.mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip.isRunning()){
                    stopMusic();
                    board.mute.setText("Un-mute");
                }
                else{
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    board.mute.setText("Mute");
                }

            }
        });
        playMusic();
        updateBoard();
    }
    /**
     * A dialog window that allows the player to select a pawn for a specified path.
     * This menu provides the player with options to choose between an "Arch" (archaeologist)
     * or "Thesseus" pawn, based on the number of available archaeologists and whether the
     * "Thesseus" pawn is available.
     * <p><b>Pre-condition</b>:
     * - The player must have a valid number of archaeologists and the possibility of selecting "Theseus" as a pawn.
     * </p>
     * <p><b>Post-condition</b>:
     * - The player selects a pawn via a dialog, and the choice is recorded.
     * </p>
     * <p><b>Invariant</b>:
     * - The dialog remains visible until the player makes a selection.
     * - The player's options are dynamically set based on the number of available archaeologists and the availability of "Theseus."
     * </p>
     * @see Player
     */
    class PawnSelectionMenu extends JOptionPane {
        int choice;
        String options[];
        /**
         * Constructs a PawnSelectionMenu that allows the player to select a pawn.
         *
         * The dialog displays the available pawn options ("Arch" and "Thesseus") and
         * shows the number of available archaeologists and the status of the "Thesseus" pawn.
         * The player's selection is captured through this constructor.
         *
         * @param path the name of the path the pawn is being selected for.
         * @param plr the player making the pawn selection.
         */
        public PawnSelectionMenu(String path, Player plr) {
            String options[] = {"Arch", "Theseus"};
            choice = JOptionPane.showOptionDialog(null, "Player "+ plr.getName() +" Select pawn for path " + path + "\nAvailable Archaiologists: " + (3-plr.amountOfArchaeologists()) + "\nTheseus available: " + plr.hasThesseus(), "Pawn Selector", 0,3, null, options, options[0]);
        }
        public int getChoice() {
            return choice;
        }
        public void setChoice(int choice) {
            this.choice = choice;
        }
    }

    /**
     * Starts a new game by initializing the game board, initializing the players, and selecting the starting player.
     * It also initializes the board visibility and prints out details of the game setup, such as the number of cards each player has.
     *
     * <p><b>Pre-condition</b>:
     * - No pre-condition is required. This method can be called at any point to initiate a new game.
     * </p>
     *
     * <p><b>Post-condition</b>:
     * - The game state is reset, initializing all necessary components for a new game, including the board and players.
     * - A new game begins with the board visible and the starting player selected.
     * </p>
     *
     * <p><b>Invariant</b>:
     * - The game always starts with two players, ensuring consistent gameplay.
     * </p>
     *
     * @throws LineUnavailableException if there is an issue with audio playback (if relevant to the game setup).
     * @throws IOException if an I/O error occurs during initialization (e.g., loading game data).
     * @throws FailedToAddFindingException if there is an error in adding findings during the initialization phase.
     */
    public void startNewGame() throws LineUnavailableException, IOException, FailedToAddFindingException {
        initializeBoard();
        board.setVisible(true);
        startingPlayer();
        System.out.println("New Game Started");
        System.out.println(player1.getName() + " " + player1.getCards().size());
        System.out.println(player2.getName() + " " + player2.getCards().size());
        System.out.println();
    }

    /**
     * Determines whether a pawn can move based on the current and previous cards played,
     * following the game rules. The method also updates the game state based on the movement,
     * including pawn locations, player points, and special card effects.
     *
     * <p><b>Pre-condition</b>:
     * - Both the current card (c1) and previous card (c2) must not be null.
     * - The current game state should be valid with players taking turns and using valid cards.
     * - The game path and positions must be correctly set for each palace.
     * </p>
     *
     * <p><b>Post-condition</b>:
     * - The method processes the movement of the pawn as per the card rules, updating its position
     *   and player points.
     * - The method handles special cards like Ariadne or Minotaur, triggering their respective effects.
     * - The game state is updated, including the removal of the used card and updating the display.
     * </p>
     *
     * <p><b>Invariant</b>:
     * - The method evaluates whether the pawn can move according to the current and previous card rules
     *   without modifying the cards themselves.
     * - Turn order and other game state invariants remain consistent during execution.
     * </p>
     *
     * @param c1 the current card being played (could be a NumberCard or SpecialCard).
     * @param c2 the previous card that was played.
     * @param pawns the list of pawns currently in play for the player.
     * @param panel the JPanel containing the game interface elements, used for updating the UI.
     * @throws IllegalArgumentException if no pawn can be moved or if there is an invalid card or palace.
     * @throws RuntimeException if any unexpected error occurs during card handling.
     */
    public void matchCard(Card c1, Card c2, ArrayList<Pawn> pawns, JPanel panel) {
        Pawn pawnToMove = null;
        Pawn pawnToAttack = null;
        for (int i = 0; i < pawns.size(); i++) {
            if (pawns.get(i).getPalace().equals(c1.getPalaceName())) {
                pawnToMove = pawns.get(i);
            }
        }
        if (c1 instanceof NumberCard) {
            if (c1.getPalaceName().equals(c2.getPalaceName())) {
                if (c2 instanceof NumberCard) {
                    if (((NumberCard) c1).getPoints() >= ((NumberCard) c2).getPoints()) {
                        if (pawnToMove == null) {
                            throw new IllegalArgumentException("Pawn to move cannot be null.");
                        }
                        if (pawnToMove.getX() == 9) {
                            System.out.println("Already reached end of path!");
                        } else {
                            int x = pawnToMove.getX();
                            System.out.println("Pos of pawn before movement: X=" + x);
                            Path currentPath;
                            if (c1.getPalaceName().equals("knossos")) {
                                currentPath = knossos.getPath();
                            } else if (c1.getPalaceName().equals("malia")) {
                                currentPath = malia.getPath();
                            } else if (c1.getPalaceName().equals("phaistos")) {
                                currentPath = phaistos.getPath();
                            } else if (c1.getPalaceName().equals("zakros")) {
                                currentPath = zakros.getPath();
                            } else {
                                throw new IllegalArgumentException("Invalid palace name: " + c1.getPalaceName());
                            }
                            Position currentPosition = currentPath.getPositions().get(x);
                            Square currentSquare = currentPosition.getSquare();
                            if (player.getName().equals("1")) {
                                currentSquare.setPawn1(null);
                            } else if (player.getName().equals("2")) {
                                currentSquare.setPawn2(null);
                            }
                            x++;
                            pawnToMove.setX(x);
                            Position nextPosition = currentPath.getPositions().get(x);
                            Square nextSquare = nextPosition.getSquare();
                            if (x == 6) {
                                player.setCheckPoints(player.getCheckPoints() + 1);
                                System.out.println("Check added for player " + player.getName());
                            }
                            if (player.getName().equals("1")) {
                                nextSquare.setPawn1(pawnToMove);
                            } else if (player.getName().equals("2")) {
                                nextSquare.setPawn2(pawnToMove);
                            }
                            if (player.getName().equals("1")){
                                pawnToMove.getPawn().setLocation(nextSquare.getSquareLabel().getBounds().x + 10, nextSquare.getSquareLabel().getBounds().y+20);
                            }
                            else if (player.getName().equals("2")){
                                pawnToMove.getPawn().setLocation(nextSquare.getSquareLabel().getBounds().x + 50, nextSquare.getSquareLabel().getBounds().y+20);
                            }
                            System.out.println("Pos of pawn after movement: X=" + x);
                            if (pawnToMove instanceof Thesseus) {
                                player.setPoints(player.getPoints() + nextPosition.getPoints()*2);
                            }
                            else {
                                player.setPoints(player.getPoints() + nextPosition.getPoints());
                            }
                        }
                        if (c1.getPalaceName().equals("knossos")) {
                            if (player.getName().equals("1")) {
                                player1.getLastPlayedCards()[0] = c1;
                                pl1.getKnossosC().setIcon(c1.getImage());
                            }
                            else if (player.getName().equals("2")) {
                                player2.getLastPlayedCards()[0] = c1;
                                pl2.getKnossosC().setIcon(c1.getImage());
                            }
                        }if (c1.getPalaceName().equals("malia")) {
                            if (player.getName().equals("1")) {
                                player1.getLastPlayedCards()[1] = c1;
                                pl1.getMaliaC().setIcon(c1.getImage());
                            }
                            else if (player.getName().equals("2")) {
                                player2.getLastPlayedCards()[1] = c1;
                                pl2.getMaliaC().setIcon(c1.getImage());
                            }
                        }if (c1.getPalaceName().equals("phaistos")) {
                            if (player.getName().equals("1")) {
                                player1.getLastPlayedCards()[2] = c1;
                                pl1.getPhaistosC().setIcon(c1.getImage());
                            }
                            else if (player.getName().equals("2")) {
                                player2.getLastPlayedCards()[2] = c1;
                                pl2.getPhaistosC().setIcon(c1.getImage());
                            }
                        }if (c1.getPalaceName().equals("zakros")) {
                            if (player.getName().equals("1")) {
                                player1.getLastPlayedCards()[3] = c1;
                                pl1.getZakrosC().setIcon(c1.getImage());
                            }
                            else if (player.getName().equals("2")) {
                                player2.getLastPlayedCards()[3] = c1;
                                pl2.getZakrosC().setIcon(c1.getImage());
                            }
                        }
                        for (int i = 0; i < player.getCards().size(); i++) {
                            if (player.getCards().get(i) == c1) {
                                player.getCards().remove(i);
                            }
                        }

                        for (Component component : panel.getComponents()) {
                            if (component instanceof JButton) {
                                JButton button = (JButton) component;
                                Card card = (Card) button.getClientProperty("card");
                                if (card == c1) {
                                    panel.remove(button);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else if (c1 instanceof SpecialCard) {
            if (c1.getPalaceName().equals(c2.getPalaceName())) {
                if (c2 instanceof NumberCard) {
                    if (c1 instanceof Ariadne) {
                        if (pawnToMove == null) {
                            throw new IllegalArgumentException("Pawn to move cannot be null.");
                        }
                        Square nextSquare = null;
                        int x = 0;
                        if (pawnToMove.getX() == 9) {
                            System.out.println("Already reached end of path!");
                            thrownCard = true;
                        } else {
                            x = pawnToMove.getX();
                            System.out.println("Pos of pawn before movement: X=" + x);
                            Path currentPath;
                            if (c1.getPalaceName().equals("knossos")) {
                                currentPath = knossos.getPath();
                            } else if (c1.getPalaceName().equals("malia")) {
                                currentPath = malia.getPath();
                            } else if (c1.getPalaceName().equals("phaistos")) {
                                currentPath = phaistos.getPath();
                            } else if (c1.getPalaceName().equals("zakros")) {
                                currentPath = zakros.getPath();
                            } else {
                                throw new IllegalArgumentException("Invalid palace name: " + c1.getPalaceName());
                            }
                            Position currentPosition = currentPath.getPositions().get(x);
                            Square currentSquare = currentPosition.getSquare();
                            for (int i = 0; i < 2; i++) {
                                if (player.getName().equals("1")) {
                                    currentSquare.setPawn1(null);
                                } else if (player.getName().equals("2")) {
                                    currentSquare.setPawn2(null);
                                }
                                x++;
                                pawnToMove.setX(x);
                                Position nextPosition = currentPath.getPositions().get(x);
                                nextSquare = nextPosition.getSquare();
                                if (pawnToMove instanceof Thesseus) {
                                    player.setPoints(player.getPoints() + nextPosition.getPoints()*2);
                                }
                                else{
                                    player.setPoints(player.getPoints() + nextPosition.getPoints());
                                }
                                if (x == 6) {
                                    player.setCheckPoints(player.getCheckPoints() + 1);
                                    System.out.println("Check added for player " + player.getName());
                                }
                                if (player.getName().equals("1")) {
                                    nextSquare.setPawn1(pawnToMove);
                                } else if (player.getName().equals("2")) {
                                    nextSquare.setPawn2(pawnToMove);
                                }
                                if (nextPosition instanceof FindingPosition) {
                                    if (((FindingPosition) nextPosition).getFind() != null) {
                                        findingDialog fD = new findingDialog(pawnToMove, (FindingPosition) nextPosition);
                                        if (pawnToMove instanceof Arch) {
                                            int option = fD.getChoice();
                                            System.out.println(option);
                                            if (option == 0) {
                                                try {
                                                    HistoricalFindInfo info = new HistoricalFindInfo(player, (FindingPosition) nextPosition);
                                                    player.getFinds().add(((FindingPosition) nextPosition).getFind());
                                                    if (((FindingPosition) nextPosition).getFind().getFindingName().equals("snakes")) {
                                                        player.setAmountOfStatues(player.getAmountOfStatues() + 1);
                                                    }
                                                    ((FindingPosition) nextPosition).setFind(null);
                                                    pawnToMove.setPowerActivated(true);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                            } else if (option == 1) {
                                                System.out.println("Finding Ignored!");
                                            }
                                        } else if (pawnToMove instanceof Thesseus) {
                                            if (((Thesseus) pawnToMove).getFindingsDestroyed() == 3) {
                                                System.out.println("Destroyed max findings!");
                                            } else {
                                                int option = fD.getChoice();
                                                if (option == 0) {
                                                    ((FindingPosition) nextPosition).setFind(null);
                                                    System.out.println("Finding destroyed!");
                                                    int findings = ((Thesseus) pawnToMove).getFindingsDestroyed();
                                                    findings++;
                                                    ((Thesseus) pawnToMove).setFindingsDestroyed(findings);
                                                    pawnToMove.setPowerActivated(true);
                                                } else if (option == 1) {
                                                    System.out.println("Finding Ignored!");
                                                }
                                            }
                                            pawnToMove.setPowerActivated(true);
                                        }
                                    } else {
                                        System.out.println("Ariadne Movement");
                                    }
                                }
                            }
                            if (player.getName().equals("1")) {
                                pawnToMove.getPawn().setLocation(nextSquare.getSquareLabel().getBounds().x + 10, nextSquare.getSquareLabel().getBounds().y);
                            } else if (player.getName().equals("2")) {
                                pawnToMove.getPawn().setLocation(nextSquare.getSquareLabel().getBounds().x + 50, nextSquare.getSquareLabel().getBounds().y);
                            }
                            System.out.println("Pos of pawn after movement: X=" + x);
                            thrownCard = true;
                        }
                    } else if (c1 instanceof Minotaur) {
                        //Supposedly would include Minotaur Logic
                        System.out.println("Minotaur");
                    }
                }
            }
            for (int i = 0; i < player.getCards().size(); i++) {
                if (player.getCards().get(i) == c1) {
                    player.getCards().remove(i);
                }
            }

            for (Component component : panel.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    Card card = (Card) button.getClientProperty("card");
                    if (card == c1) {
                        panel.remove(button);
                        break;
                    }
                }
            }
        }
    }
    /**
     * Throws a card for the player and places a pawn on the corresponding path of the palace or moves the pawn if a pawn has already been added
     * <p>
     * and also handles card logic.
     * <p>
     * This method handles the logic of selecting and playing a card for the player. It ensures that the card
     * matches the conditions of the palace (e.g., whether there is already a pawn on the path) and places the
     * player's pawn accordingly. Depending on the palace and card type, it might prompt the user to select a pawn
     * to place. The player earns points pawn's position on the path.
     * <p>
     * <b>Pre-condition:</b> The player must have a valid card, and the selected palace must allow placing a pawn
     * on the path. The player must not have already thrown a card during this turn, and the card must not be a special card
     * <p>
     * <b>Post-condition:</b> The pawn is placed on the palace path, the player's points are updated, and the card is removed
     * from the player's hand. The pawn's position is updated on the board, and the turn is considered completed.
     * <p>
     * <b>Invariant:</b> The method ensures that the player cannot throw a card when the card stack is empty, cannot
     * throw a special card when not allowed, and cannot place a pawn on a path if another pawn is already present.
     *
     * @param c1 the card to be thrown
     * @param player the player who is throwing the card
     * @param panel the panel where the card's button will be removed
     */
    int amountOfArchs1 = 0;
    int amountOfArchs2 = 0;
    boolean theseusAdded1 = false;
    boolean theseusAdded2 = false;
    ArrayList<Pawn> pawnLocs1 = new ArrayList<Pawn>(); //0: knossos, 1: malia, 2: phaistos, 3: zakros
    ArrayList<Pawn> pawnLocs2 = new ArrayList<Pawn>(); //0: knossos, 1: malia, 2: phaistos, 3: zakros
    public void throwCard(Card c1, Player player, JPanel panel) {
        String path = c1.getPalaceName();
        Arch arch1 = new Arch(new ImageIcon("src/assets/images/pionia/arch.jpg"), player1);
        Arch arch2 = new Arch(new ImageIcon("src/assets/images/pionia/arch.jpg"), player2);
        Thesseus theseus1 = new Thesseus(new ImageIcon("src/assets/images/pionia/theseus.jpg"), player1);
        Thesseus theseus2 = new Thesseus(new ImageIcon("src/assets/images/pionia/theseus.jpg"), player2);
        switch (path) {
            case "knossos":
                if (player.getName().equals("1")) {
                    if (knossos.getPath().HasPawn1() && !thrownCard) {
                        matchCard(c1, player1.getLastPlayedCards()[0], pawnLocs1, panel);
                    } else if (!knossos.getPath().HasPawn1() && !thrownCard) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cant start path with special card!");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("knossos", player1);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs1 == 3) {
                                    theseus1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(theseus1);
                                    theseusAdded1 = true;
                                } else {
                                    arch1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(arch1);
                                    amountOfArchs1++;
                                }
                            } else if (option == 1 && !theseusAdded1) {
                                theseus1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(theseus1);
                                theseusAdded1 = true;
                            } else if (option == 1 && theseusAdded1) {
                                arch1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(arch1);
                                amountOfArchs1++;
                            }
                            if (!knossos.getPath().HasPawn1()) {
                                Pawn pawn = player1.getPawns().get(player1.getPawns().size() - 1);
                                board.getPlayer1Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(10, 30, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                if (pawn instanceof Thesseus) {
                                    player1.setPoints(player1.getPoints() + knossos.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player1.setPoints(player1.getPoints() + knossos.getPath().getPositions().get(0).getPoints());
                                }
                                pawn.setPosition(positions1[pawn.getY()][pawn.getX()]);
                                pawnLocs1.add(pawn);
                                player1.getLastPlayedCards()[0] = c1;
                                pl1.getKnossosC().setIcon(c1.getImage());
                                for (int i = 0; i < player1.getCards().size(); i++) {
                                    if (c1.equals(player1.getCards().get(i))) {
                                        player1.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                knossos.getPath().setHasPawn1(true);
                                thrownCard = true;
                            }
                        }
                    }
                } else if (player.getName().equals("2")) {
                    if (knossos.getPath().HasPawn2() && !thrownCard) {
                        matchCard(c1, player2.getLastPlayedCards()[0], pawnLocs2, panel);
                    } else if (!knossos.getPath().HasPawn2()) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cannot start path with SpecialCard");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("knossos", player2);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs2 == 3) {
                                    theseus2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(theseus2);
                                    theseusAdded2 = true;
                                } else {
                                    arch2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(arch2);
                                    amountOfArchs2++;
                                }
                            } else if (option == 1 && !theseusAdded2) {
                                theseus2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(theseus2);
                                theseusAdded2 = true;
                            } else if (option == 1 && theseusAdded2) {
                                arch2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(arch2);
                                amountOfArchs2++;
                            }
                            if (!knossos.getPath().HasPawn2()) {
                                Pawn pawn = player2.getPawns().get(player2.getPawns().size() - 1);
                                board.getPlayer2Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(60, 30, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions2[pawn.getY()][pawn.getX()]);
                                if (pawn instanceof Thesseus) {
                                    player2.setPoints(player2.getPoints() + knossos.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player2.setPoints(player2.getPoints() + knossos.getPath().getPositions().get(0).getPoints());
                                }
                                pawnLocs2.add(pawn);
                                player2.getLastPlayedCards()[0] = c1;
                                pl2.getKnossosC().setIcon(c1.getImage());
                                for (int i = 0; i < player2.getCards().size(); i++) {
                                    if (c1.equals(player2.getCards().get(i))) {
                                        player2.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                knossos.getPath().setHasPawn2(true);
                                thrownCard = true;
                            }
                        }
                    }
                }

                updateBoard();
                break;
            case "malia":
                if (player.getName().equals("1")) {
                    if (malia.getPath().HasPawn1() && !thrownCard) {
                        matchCard(c1, player1.getLastPlayedCards()[1], pawnLocs1, panel);
                    } else if (!malia.getPath().HasPawn1() && !thrownCard) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cant start path with special card!");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("malia", player1);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs1 == 3) {
                                    theseus1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(theseus1);
                                    theseusAdded1 = true;
                                } else {
                                    arch1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(arch1);
                                    amountOfArchs1++;
                                }
                            } else if (option == 1 && !theseusAdded1) {
                                theseus1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(theseus1);
                                theseusAdded1 = true;
                            } else if (option == 1 && theseusAdded1) {
                                arch1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(arch1);
                                amountOfArchs1++;
                            }
                            if (!malia.getPath().HasPawn1()) {
                                Pawn pawn = player1.getPawns().get(player1.getPawns().size() - 1);
                                board.getPlayer1Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(10, 140, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions1[pawn.getY()][pawn.getX()]);
                                pawnLocs1.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player1.setPoints(player1.getPoints() + malia.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player1.setPoints(player1.getPoints() + malia.getPath().getPositions().get(0).getPoints());
                                }
                                player1.getLastPlayedCards()[1] = c1;
                                pl1.getMaliaC().setIcon(c1.getImage());
                                for (int i = 0; i < player1.getCards().size(); i++) {
                                    if (c1.equals(player1.getCards().get(i))) {
                                        player1.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                malia.getPath().setHasPawn1(true);
                                thrownCard = true;
                            }
                        }
                    }
                } else if (player.getName().equals("2")) {
                    if (malia.getPath().HasPawn2() && !thrownCard) {
                        matchCard(c1, player2.getLastPlayedCards()[1], pawnLocs2, panel);
                    } else if (!malia.getPath().HasPawn2()) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cannot start path with SpecialCard");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("malia", player2);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs2 == 3) {
                                    theseus2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(theseus2);
                                    theseusAdded2 = true;
                                } else {
                                    arch2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(arch2);
                                    amountOfArchs2++;
                                }
                            } else if (option == 1 && !theseusAdded2) {
                                theseus2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(theseus2);
                                theseusAdded2 = true;
                            } else if (option == 1 && theseusAdded2) {
                                arch2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(arch2);
                                amountOfArchs2++;
                            }
                            if (!malia.getPath().HasPawn2()) {
                                Pawn pawn = player2.getPawns().get(player2.getPawns().size() - 1);
                                board.getPlayer2Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(60, 140, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions2[pawn.getY()][pawn.getX()]);
                                pawnLocs2.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player2.setPoints(player2.getPoints() + malia.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player2.setPoints(player2.getPoints() + malia.getPath().getPositions().get(0).getPoints());
                                }
                                player2.getLastPlayedCards()[1] = c1;
                                pl2.getMaliaC().setIcon(c1.getImage());
                                for (int i = 0; i < player2.getCards().size(); i++) {
                                    if (c1.equals(player2.getCards().get(i))) {
                                        player2.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                malia.getPath().setHasPawn2(true);
                                thrownCard = true;
                            }
                        }
                    }
                }
                updateBoard();
                break;
            case "phaistos":
                if (player.getName().equals("1")) {
                    if (phaistos.getPath().HasPawn1() && !thrownCard) {
                        matchCard(c1, player1.getLastPlayedCards()[2], pawnLocs1, panel);
                    } else if (!phaistos.getPath().HasPawn1()) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cant start path with special card!");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("phaistos", player1);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs1 == 3) {
                                    theseus1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(theseus1);
                                    theseusAdded1 = true;
                                } else {
                                    arch1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(arch1);
                                    amountOfArchs1++;
                                }
                            } else if (option == 1 && !theseusAdded1) {
                                theseus1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(theseus1);
                                theseusAdded1 = true;
                            } else if (option == 1 && theseusAdded1) {
                                arch1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(arch1);
                                amountOfArchs1++;
                            }
                            if (!phaistos.getPath().HasPawn1()) {
                                Pawn pawn = player1.getPawns().get(player1.getPawns().size() - 1);
                                board.getPlayer1Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(10, 250, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions1[pawn.getY()][pawn.getX()]);
                                pawnLocs1.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player1.setPoints(player1.getPoints() + phaistos.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player1.setPoints(player1.getPoints() + phaistos.getPath().getPositions().get(0).getPoints());
                                }
                                player1.getLastPlayedCards()[2] = c1;
                                pl1.getPhaistosC().setIcon(c1.getImage());
                                for (int i = 0; i < player1.getCards().size(); i++) {
                                    if (c1.equals(player1.getCards().get(i))) {
                                        player1.getCards().remove(i);
                                        break;
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                phaistos.getPath().setHasPawn1(true);
                                thrownCard = true;
                            }
                        }
                    }
                } else if (player.getName().equals("2")) {
                    if (phaistos.getPath().HasPawn2()) {
                        matchCard(c1, player2.getLastPlayedCards()[2], pawnLocs2, panel);
                    } else if (!phaistos.getPath().HasPawn2()) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cannot start path with SpecialCard");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("phaistos", player2);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs2 == 3) {
                                    theseus2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(theseus2);
                                    theseusAdded2 = true;
                                } else {
                                    arch2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(arch2);
                                    amountOfArchs2++;
                                }
                            } else if (option == 1 && !theseusAdded2) {
                                theseus2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(theseus2);
                                theseusAdded2 = true;
                            } else if (option == 1 && theseusAdded2) {
                                arch2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(arch2);
                                amountOfArchs2++;
                            }
                            if (!phaistos.getPath().HasPawn2()) {
                                Pawn pawn = player2.getPawns().get(player2.getPawns().size() - 1);
                                board.getPlayer2Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(60, 250, 30, 50);
                                pawn.setY(2);
                                pawn.setX(0);
                                pawn.setPosition(positions2[pawn.getY()][pawn.getX()]);
                                pawnLocs2.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player2.setPoints(player2.getPoints() + phaistos.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player2.setPoints(player2.getPoints() + phaistos.getPath().getPositions().get(0).getPoints());
                                }
                                player2.getLastPlayedCards()[2] = c1;
                                pl2.getPhaistosC().setIcon(c1.getImage());
                                for (int i = 0; i < player2.getCards().size(); i++) {
                                    if (c1.equals(player2.getCards().get(i))) {
                                        player2.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                phaistos.getPath().setHasPawn2(true);
                                thrownCard = true;
                            }
                        }
                    }
                }
                updateBoard();
                break;
            case "zakros":
                if (player.getName().equals("1")) {
                    if (zakros.getPath().HasPawn1() && !thrownCard) {
                        matchCard(c1, player1.getLastPlayedCards()[3], pawnLocs1, panel);
                    } else if (!zakros.getPath().HasPawn1() && !thrownCard) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cant start path with special card!");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("zakros", player1);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs1 == 3) {
                                    theseus1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(theseus1);
                                    theseusAdded1 = true;
                                } else {
                                    arch1.setPalace(c1.getPalaceName());
                                    player1.getPawns().add(arch1);
                                    amountOfArchs1++;
                                }
                            } else if (option == 1 && !theseusAdded1) {
                                theseus1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(theseus1);
                                theseusAdded1 = true;
                            } else if (option == 1 && theseusAdded1) {
                                arch1.setPalace(c1.getPalaceName());
                                player1.getPawns().add(arch1);
                                amountOfArchs1++;
                            }
                            if (!zakros.getPath().HasPawn1()) {
                                Pawn pawn = player1.getPawns().get(player1.getPawns().size() - 1);
                                board.getPlayer1Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(10, 365, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions1[pawn.getY()][pawn.getX()]);
                                pawnLocs1.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player1.setPoints(player1.getPoints() + zakros.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player1.setPoints(player1.getPoints() + zakros.getPath().getPositions().get(0).getPoints());
                                }
                                player1.getLastPlayedCards()[3] = c1;
                                pl1.getZakrosC().setIcon(c1.getImage());
                                for (int i = 0; i < player1.getCards().size(); i++) {
                                    if (c1.equals(player1.getCards().get(i))) {
                                        player1.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                zakros.getPath().setHasPawn1(true);
                                thrownCard = true;
                            }
                        }
                    }
                } else if (player.getName().equals("2")) {
                    if (zakros.getPath().HasPawn2() && !thrownCard) {
                        matchCard(c1, player2.getLastPlayedCards()[3], pawnLocs2, panel);
                    } else if (!zakros.getPath().HasPawn2()) {
                        if (c1 instanceof SpecialCard) {
                            System.out.println("Cannot start path with SpecialCard");
                        } else {
                            PawnSelectionMenu menu = new PawnSelectionMenu("zakros", player2);
                            int option = menu.getChoice();
                            if (option == 0) {
                                if (amountOfArchs2 == 3) {
                                    theseus2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(theseus2);
                                    theseusAdded2 = true;
                                } else {
                                    arch2.setPalace(c1.getPalaceName());
                                    player2.getPawns().add(arch2);
                                    amountOfArchs2++;
                                }
                            } else if (option == 1 && !theseusAdded2) {
                                theseus2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(theseus2);
                                theseusAdded2 = true;
                            } else if (option == 1 && theseusAdded2) {
                                arch2.setPalace(c1.getPalaceName());
                                player2.getPawns().add(arch2);
                                amountOfArchs2++;
                            }
                            if (!zakros.getPath().HasPawn2()) {
                                Pawn pawn = player2.getPawns().get(player2.getPawns().size() - 1);
                                board.getPlayer2Pawns().add(pawn.getPawn());
                                pawn.getPawn().setBounds(60, 365, 30, 50);
                                pawn.setY(1);
                                pawn.setX(0);
                                pawn.setPosition(positions2[pawn.getY()][pawn.getX()]);
                                pawnLocs2.add(pawn);
                                if (pawn instanceof Thesseus) {
                                    player2.setPoints(player2.getPoints() + zakros.getPath().getPositions().get(0).getPoints()*2);
                                }
                                else {
                                    player2.setPoints(player2.getPoints() + zakros.getPath().getPositions().get(0).getPoints());
                                }
                                player2.getLastPlayedCards()[3] = c1;
                                pl2.getZakrosC().setIcon(c1.getImage());
                                for (int i = 0; i < player2.getCards().size(); i++) {
                                    if (c1.equals(player2.getCards().get(i))) {
                                        player2.getCards().remove(i);
                                    }
                                }
                                for (Component component : panel.getComponents()) {
                                    if (component instanceof JButton) {
                                        JButton button = (JButton) component;
                                        Card card = (Card) button.getClientProperty("card");
                                        if (card == c1) {
                                            panel.remove(button);
                                            break;
                                        }
                                    }
                                }
                                zakros.getPath().setHasPawn2(true);
                                thrownCard = true;
                            }
                        }
                    }
                }
                updateBoard();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + path);
        }
        System.out.println(player1.getName() + " " + player1.getCards().size());
        System.out.println(player2.getName() + " " + player2.getCards().size());
        updateBoard();
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Pulls a new card from the card stack and adds it to the player's hand.
     * <p>
     * This method ensures that the player can only pull a new card if they have fewer than 8 cards
     * and have not already disposed of a card during the current game session. The new card is added
     * to the player's hand, and the corresponding button is added to the player panel.
     * <p>
     * <b>Pre-condition:</b> The player must have fewer than 8 cards in their hand, the player must not
     * have already disposed of a card during the session, and there must be available cards in the stack.
     * <p>
     * <b>Post-condition:</b> A new card is pulled from the card stack, added to the player's hand, and
     * the corresponding button representing the card is added to the display panel. The panel is then updated
     * to reflect the changes. The turn is changed if it is the player's first move.
     * <p>
     * <b>Invariant:</b> The method ensures that the player cannot exceed the maximum card limit of 8,
     * cannot pull a card after disposing of one, and cannot pull a card if the card stack is empty.
     *
     * @param player the player who is pulling the card
     * @param panel the panel where the card's button will be added
     */
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
            if (disposedCards==1){
                changeTurn();
            }
        }
    }

    /**
     * Disposes of a card from the player's hand and removes it from the player panel.
     * <p>
     * This method ensures that a player can dispose of a card only if they have
     * 8 cards in their hand and have not yet disposed of a card during the current game session.
     * The specified card is removed from both the player's list of cards and the player panel.
     * <p>
     * <b>Pre-condition:</b> The player must have exactly 8 cards in their hand, and the card
     * to be disposed of must be part of the player's hand. The card must also be represented on the playerPanel.
     * <p>
     * <b>Post-condition:</b> The card is removed from the player's hand, and the corresponding
     * button representing the card is removed from the panel. The panel is then updated to reflect the changes.
     * <p>
     * <b>Invariant:</b> The method ensures that a card can only be disposed of once per session,
     * and the player must have enough cards to do so.
     *
     * @param c1 the card to be disposed of
     * @param player the player who is disposing of the card
     * @param panel the panel from which the card's button should be removed
     */
    public void disposeCard(Card c1, Player player, JPanel panel) {
        if (disposedCards == 1){
            System.out.println("Player has already disposed a card");
        }
        else if (player.getCards().size() < 8)
        {
            System.out.println("Less than 8 cards");
        }
        else
        {
            for (int i = 0; i < player.getCards().size(); i++) {
                if (player.getCards().get(i) == c1) {
                    player.getCards().remove(i);
                    System.out.println("Card Disposed");
                    System.out.println(player.getCards().size());
                }
            }
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
        }
    }

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
                throwCard(card, player, panel);
            } else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                disposeCard(card, player, panel);
                board.repaint();
            }
            // Consume the event to prevent propagation
            e.consume();
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
     * A mouse listener for handling click events on the card stuck button in the game.
     * <p>
     * This listener is responsible for responding to left-click mouse events on the button.
     * When the player clicks the button a card is pulled for the player
     * and the game board is repainted.
     * <p>
     * <b>Pre-condition:</b> The source of the mouse event must be a valid `JButton` representing
     * the stack. The player must be properly identified to either `player1` or `player2`.
     * <p>
     * <b>Post-condition:</b> The corresponding player pulls a card, the board is updated,
     * and the event is consumed to prevent further propagation.
     * <p>
     * <b>Invariant:</b> The listener ensures that only valid button clicks are handled
     * and that the appropriate player's action is taken based on the current game state.
     */
    private class StackListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!(e.getSource() instanceof JButton)) {
                System.out.println("Not a button");
            } else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                JButton button = (JButton) e.getSource();
                if (player.getName().equals("1")){
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
     * A mouse listener for handling interactions with positions in the game.
     * <p>
     * This listener is attached to the squares of the path positions and responds to
     * mouse click events. Depending on the type of pawn and the finding at the position,
     * it either activates a historical find or destroys the finding.
     * <p>
     * <b>Pre-condition:</b> The given `FindingPosition` must be initialized with a valid
     * finding or null. The player's pawn must be properly set to either an Archaeologist or Thesseus.
     * <p>
     * <b>Post-condition:</b> Depending on the interaction, a finding may be added to the player's collection,
     * a finding may be destroyed, or the action may be ignored.
     * <p>
     * <b>Invariant:</b> The listener ensures that only valid actions are performed based on the type of pawn
     * and the finding at the position. It interacts with the UI and updates the game state accordingly.
     */
    private class PathListener implements MouseListener {
        private JDialog findingDialog = new JDialog();
        private FindingPosition pos;
        private playerPanel plr;
        public PathListener(FindingPosition pos) {
            this.pos = pos;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (pos.getFind() == null) {
                    System.out.println("Position: " + pos.getX() + " has no finding");
                }
                else if (pos.getFind() instanceof Fresco) {
                    File cam = new File("src/assets/music/cam.wav");
                    AudioInputStream cameraStream = null;
                    try {
                        cameraStream = AudioSystem.getAudioInputStream(cam);
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Clip camClip;
                    try {
                        camClip = AudioSystem.getClip();
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        camClip.open(cameraStream);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Pawn pawn = null;
                    if (player.getName().equals("1")) {
                        pawn = pos.getSquare().getPawn1();
                    }
                    else if (player.getName().equals("2")) {
                        pawn = pos.getSquare().getPawn2();
                    }

                    if (pawn instanceof Arch) {
                        findingDialog finding = new findingDialog(pawn, pos);
                        int option = finding.getChoice();
                        System.out.println(option);
                        if (option == 0) {
                            try {
                                camClip.start();
                                HistoricalFindInfo info = new HistoricalFindInfo(player, pos);
                                player.getFinds().add(pos.getFind());
                                player.setPoints(player.getPoints() + pos.getFind().getPoints());
                                if (pos.getFind().getFindingName().equals("snakes")){
                                    player.setAmountOfStatues(player.getAmountOfStatues() + 1);
                                }
                                pawn.setPowerActivated(true);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else if (option == 1) {
                            System.out.println("Finding Ignored!");
                        }
                    }
                    else if (pawn instanceof Thesseus) {
                        if (((Thesseus) pawn).getFindingsDestroyed() == 3){
                            System.out.println("Max Findings Destroyed!");
                        }
                        else{
                            findingDialog finding = new findingDialog(pawn, pos);
                            int option = finding.getChoice();
                            if (option == 0) {
                                pos.setFind(null);
                                System.out.println("Finding destroyed!");
                                int findings = ((Thesseus) pawn).getFindingsDestroyed();
                                findings++;
                                ((Thesseus) pawn).setFindingsDestroyed(findings);
                                pawn.setPowerActivated(true);
                            }
                            else if (option == 1) {
                                System.out.println("Finding Ignored!");
                            }
                        }
                    }
                }
                else {
                    Pawn pawn = null;
                    if (player.getName().equals("1")) {
                        pawn = pos.getSquare().getPawn1();
                    } else if (player.getName().equals("2")) {
                        pawn = pos.getSquare().getPawn2();
                    }
                    findingDialog finding = new findingDialog(pawn, pos);
                    if (pawn instanceof Arch) {
                        int option = finding.getChoice();
                        System.out.println(option);
                        if (option == 0) {
                            try {
                                HistoricalFindInfo info = new HistoricalFindInfo(player, pos);
                                player.getFinds().add(pos.getFind());
                                player.setPoints(player.getPoints() + pos.getFind().getPoints());
                                if (pos.getFind().getFindingName().equals("snakes")){
                                    player.setAmountOfStatues(player.getAmountOfStatues() + 1);
                                }
                                pos.setFind(null);
                                pawn.setPowerActivated(true);
                                pawn.showPawn();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (option == 1) {
                            System.out.println("Finding Ignored!");
                        }
                    } else if (pawn instanceof Thesseus) {
                        if (((Thesseus) pawn).getFindingsDestroyed() == 3) {
                            System.out.println("Max findings destroyed!");
                        } else {
                            int option = finding.getChoice();
                            if (option == 0) {
                                pos.setFind(null);
                                System.out.println("Finding destroyed!");
                                int findings = ((Thesseus) pawn).getFindingsDestroyed();
                                findings++;
                                ((Thesseus) pawn).setFindingsDestroyed(findings);
                                pawn.setPowerActivated(true);
                            } else if (option == 1) {
                                System.out.println("Finding Ignored!");
                            }
                        }
                        pawn.setPowerActivated(true);
                    }
                }

            }
            updateBoard();
            e.consume();
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
     * Sets up mouse listeners for specific positions along the path of the given palace.
     * <p>
     * <b>Pre-condition:</b> The provided palace object must not be null, and its path
     * must be properly initialized with an ArrayList of positions.
     * <p>
     * <b>Post-condition:</b> Mouse listeners are attached to the squares of all
     * eligible positions (odd indices and the last position) along the palace's path.
     * <p>
     * <b>Invariant:</b> The method ensures that only eligible positions have mouse
     * listeners set, leaving others unchanged.
     *
     * @param Palace the palace whose path positions will have listeners set.
     */
    public void setPathListeners(palace Palace) {
        for (int i = 0; i<9; i++) {
            if (i %2 == 1 || i==8) {
                FindingPosition pos = (FindingPosition) Palace.getPath().getPositions().get(i);
                pos.getSquare().setMouseListener(new PathListener(pos));
            }
        }
    }

    /**
     * Ends the current game session and displays the end game window with the results.
     * <p>
     * <b>Pre-condition</b>: The `player1` and `player2` objects must be initialized and contain
     * the final game state, including scores and any other relevant data needed for the end game display.
     * <p>
     * <b>Post-condition</b>: The game session is terminated, and a new `endGameWin` window is created
     * and displayed, showing the results and relevant statistics for both players.
     * <p>
     * <b>Invariant</b>: The method ensures that the game properly transitions to the end state and that
     * resources associated with the game are appropriately finalized.
     */
    public void endGame() {
            System.out.println("Ending Game");
            endGameWin end = new endGameWin(player1, player2);
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
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, FailedToAddFindingException {
        Controller controller = new Controller();
        controller.startNewGame();
    }

    /**
     *
     */
    Thread timerThread;
    private boolean isTimerRunning = false;

    /**
     * Starts a countdown timer for the current player's turn and updates the game UI accordingly.
     * <p>
     * <b>Pre-condition</b>: The `player` object and the player panels (`pl1` and `pl2`) must be initialized.
     * The game resources, such as the `clip` for audio playback, must be available and properly configured.
     * <p>
     * <b>Post-condition</b>: A countdown timer begins, updating the timer display in the UI for the current player.
     * The timer stops after 30 seconds or when the turn ends. The board is updated regularly, and an alert
     * is triggered in the final 5 seconds.
     * <p>
     * <b>Invariant</b>: The timer runs on a separate thread and ensures the UI is updated without blocking
     * the main thread. Timer-related resources are properly managed during its execution.
     */
    public void timer() {
        if (isTimerRunning) {
            return;
        }
        timerThread = new Thread(() -> {
            isTimerRunning = true;
            int seconds = 30;
            for (int i = seconds; i > 0; i--) {
                if (Thread.currentThread().isInterrupted()) {
                    isTimerRunning = false;
                    return;
                }
                if (i <= 5) {
                    clip.start();
                }
                System.out.println(i + " seconds");
                if (player.getName().equals("1")) {
                    pl1.timerInstance.setText("Χρόνος: " + i);
                }
                else if (player.getName().equals("2")) {
                    pl2.timerInstance.setText("Χρόνος: " + i);
                }
                updateBoard();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    isTimerRunning = false;
                    return;
                }
            }
            changeTurn();
            System.out.println("New Turn!");
            isTimerRunning = false;
        });
        timerThread.start();
    }

    /**
     * Stops the currently running timer thread if it is active, interrupting its execution.
     * <p>
     * <b>Pre-condition</b>: A timer must be running for this method to have any effect.
     * <p>
     * <b>Post-condition</b>: The running timer thread is interrupted, and the `isTimerRunning`
     * flag is reset to `false`.
     * <p>
     * <b>Invariant</b>: This method ensures proper termination of the timer thread without
     * affecting other parts of the application. If no timer is running, calling this method has no effect.
     */
    public void stopTimer() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
        isTimerRunning = false;
    }
}
