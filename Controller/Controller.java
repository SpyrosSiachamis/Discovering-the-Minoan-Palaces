package Controller;

import Phase1.model.Player;
import Phase1.model.cards.Card;
import Phase1.model.pawns.Pawn;
import Phase1.model.positions.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
/**
 * The Controller class manages the overall game logic, including player initialization,
 * turn management, music playback, and victory conditions.
 */
public class Controller {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private File Music;
    private Clip clip;
    private boolean isPlaying = true;

    /**
     * Constructs a Controller instance and initializes the game state.
     *
     * Pre-condition: None.
     * Post-condition: Players are initialized, and a starting player is selected.
     * Invariant: There are always two players in the game.
     */
    public Controller() {
        InitializePlayers();
        startingPlayer();
    }

    /**
     * Initializes the players for the game.
     *
     * <b>Pre-condition</b>: None.
     * <b>Post-condition</b>: player1 and player2 are assigned new Player instances.
     * <b>Invariant</b>: Each player has their respective music file assigned.
     */
    private void InitializePlayers(){
        player1 = new Player(0, new ArrayList<Card>(),new ArrayList<Pawn>(), new File("Phase1/assets/music/Player1.wav"));
        player2 = new Player(0, new ArrayList<Card>(),new ArrayList<Pawn>(), new File("Phase1/assets/music/Player2.wav"));
    }

    /**
     * Randomly selects the starting player and sets their music file.
     *
     * <b>Pre-condition</b>: player1 and player2 must be initialized.
     * <b>Post-condition</b>: currentPlayer is set to either player1 or player2, and
     *                 their corresponding music file is assigned to Music.
     * <b>Invariant</b>: The starting player is always one of the two players.
     */
    private void startingPlayer(){
        Random rand = new Random();
        int turn;
        turn = rand.nextInt(2);
        if(turn == 0){
            currentPlayer = player1;
        }
        else {
            currentPlayer = player2;
        }
        Music = currentPlayer.getMusic();
    }

    /**
     * Plays the music associated with the current player in a continuous loop.
     *
     * <b>Pre-condition</b>: Music must be assigned to a valid File object.
     * <b>Post-condition</b>: The music starts playing and loops continuously.
     * <b>Invariant</b>: Only one music file plays at any given time.
     */
    public void playMusic(){
        stopMusic();
        try{
            AudioInputStream m = AudioSystem.getAudioInputStream(Music);
            clip = AudioSystem.getClip();
            clip.open(m);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Stops the currently playing music, if any.
     *
     * Pre-condition: None.
     * Post-condition: Music playback is stopped.
     * Invariant: The music file remains unchanged.
     */
    public void stopMusic(){
        if (clip != null && clip.isRunning()){
            clip.stop();
        }
    }

    /**
     * Checks if the music is currently playing.
     *
     * Pre-condition: None.
     * Post-condition: The method returns true if the music is playing, false otherwise.
     * Invariant: The music status is consistent with the clip's running state.
     *
     * @return true if the music is playing, false otherwise.
     */
    public boolean isPlaying(){
        return clip.isRunning();
    }

    /**
     * Switches the turn to the next player and updates the music accordingly.
     *
     * Pre-condition: currentPlayer must be assigned.
     * Post-condition: currentPlayer is switched to the other player, and
     *                 their music starts playing.
     * Invariant: The turn alternates between player1 and player2.
     */
    public void changeTurn(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
        Music = currentPlayer.getMusic();
        playMusic();
    }

    /**
     * Moves a player's pawn based on the card's value and the specified path.
     *
     * Pre-condition: The pawn, card, and path must be non-null and valid.
     * Post-condition: The pawn is moved along the specified path based on the card's value.
     * Invariant: The pawn always remains on a valid path.
     *
     * @param pawn the player's pawn to move.
     * @param card the card determining the movement value.
     * @param path the path on which the pawn moves.
     */
    public void movePawn(Pawn pawn, Card card, Path path){

    }

    /**
     * Runs a countdown timer for the player's turn. If time runs out, the turn ends.
     *
     * Pre-condition: The game must be running.
     * Post-condition: The current player's turn ends when the timer reaches 0.
     * Invariant: The timer always starts at 30 seconds for each turn.
     */
    public void timer(){
        while(isPlaying){
            Clip clip;
            File countdown = new File("Phase1/assets/music/countdown.wav");
            try{
                AudioInputStream m = AudioSystem.getAudioInputStream(countdown);
                clip = AudioSystem.getClip();
                clip.open(m);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
            int seconds = 30;
            for(int i = seconds; i > 0; i--){
                if (i<=5){
                    clip.start();
                }
                System.out.println(i);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                    break;
                }
            }
            changeTurn();
            System.out.println("New Turn!");
            clip.stop();
        }
    }

    /**
     * Checks if either player has won the game. If so, stops the game.
     *
     * Pre-condition: None.
     * Post-condition: The game stops if a player wins.
     * Invariant: Only one player can win the game at a time.
     *
     * @return true if the game is still running, false if there is a winner.
     */
    public boolean Winner(){
        player1.setHasWon(true);
        if(player1.getVictoryStatus()){
            System.out.println("Player 1 wins!");
            isPlaying = !isPlaying;
        }
        else if(player2.getVictoryStatus()){
            System.out.println("Player 2 wins!");
            isPlaying = !isPlaying;
        }
        return isPlaying;
    }

    /**
     * Checks if the game is still running.
     *
     * Pre-condition: None.
     * Post-condition: Returns the current game status.
     * Invariant: The game state remains consistent with the players' statuses.
     *
     * @return true if the game is running, false otherwise.
     */
    public boolean isGameRunning(){
        return Winner();
    }

    /**
     * Starts a new game by reinitializing players, selecting the starting player,
     * and playing their music.
     *
     * Pre-condition: None.
     * Post-condition: The game is reset, and a new game starts.
     * Invariant: The game always starts with two players.
     */
    public void startNewGame(){
        isPlaying = true;
        InitializePlayers();
        startingPlayer();
        playMusic();
        System.out.println("New Game Started");
    }
}
