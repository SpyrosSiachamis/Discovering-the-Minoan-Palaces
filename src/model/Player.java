package src.model;

import src.model.cards.Card;
import src.model.findings.Finding;
import src.model.pawns.Arch;
import src.model.pawns.Pawn;
import src.model.pawns.Thesseus;

import java.io.File;
import java.util.ArrayList;

public class Player {
    /**
     * Represents the name of a Player.
     */
    private String Name;
    /**
     * Represents the total points accumulated by a Player.
     * This variable tracks the scoring of the Player within the game.
     */
    private int points;
    /**
     * Holds a collection of archaeological findings associated with the player.
     * Each finding is represented as an instance of the Finding class, which includes
     * details such as points, image, and name.
     */
    private ArrayList<Finding> finds = new ArrayList<>();
    /**
     * Represents the collection of cards held by the player.
     * This field is a list of Card objects that are associated with the player.
     */
    private ArrayList<Card> cards;
    /**
     * A list of `Pawn` objects associated with a player.
     * This collection holds the pawns that belong to a player and may consist of
     * various subclasses of `Pawn`, such as `Arch` and `Thesseus`.
     */
    private ArrayList<Pawn> pawns;
    /**
     * Indicates whether the player has won the game.
     */
    private boolean hasWon = false;
    /**
     * Represents the music file associated with the player.
     */
    private File music;
    private int amountOfStatues = 0;
    private int checkPoints = 0;

    private Card[] lastPlayedCards = {null, null, null, null};
    /**
     * <b>Constructor</b>: Initializes a Player Object.
     * @param points Total Points of Player
     * @param cards Cards of Player
     * @param pawns Pawns of Player
     */
    public Player(int points, ArrayList<Card> cards, ArrayList<Pawn> pawns, File music, String Name) {
        this.points = points;
        this.cards = cards;
        this.pawns = pawns;
        this.music = music;
        this.Name = Name;
    }

    /**
     * Retrieves the total points of the Player.
     *
     * @return The current points of the player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the Player.
     * @param points Total Points of Player.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves the list of archaeological findings associated with the player.
     *
     * @return An ArrayList of Finding objects representing the archaeological findings of the player.
     */
    public ArrayList<Finding> getFinds() {
        return finds;
    }

    /**
     * Retrieves the list of cards associated with the player.
     *
     * @return An ArrayList of Card objects representing the player's cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Sets the cards of the Player.
     * @param cards Cards of Player.
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Retrieves the list of Pawns associated with the player.
     *
     * @return An ArrayList of Pawn objects representing the player's pawns.
     */
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }
    /**
     * Calculates the number of archaeological pawns in the player's collection.
     *
     * @return The count of pawns that are instances of the Arch class.
     */
    public int amountOfArchaeologists(){
        int size=0;
        for (int i =0; i<pawns.size(); i++){
            if (pawns.get(i) instanceof Arch){
                size++;
            }
        }
        return size;
    }

    /**
     * Checks if any of the pawns in the player's list are instances of Thesseus.
     *
     * @return 1 if a Thesseus pawn is found in the list, otherwise returns 0.
     */
    public int hasThesseus(){
        for (int i=0; i<pawns.size(); i++){
            if (pawns.get(i) instanceof Thesseus){
                return 0;
            }
        }
        return 1;
    }

    /**
     * Retrieves the music file associated with the player.
     *
     * @return The music file of the player.
     */
    public File getMusic() {
        return music;
    }


    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name to be set for the player.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Retrieves the current number of check points accumulated by the player.
     *
     * @return The current check points of the player.
     */
    public int getCheckPoints() {
        return checkPoints;
    }

    /**
     * Sets the number of check points for the player.
     *
     * @param checkPoints The number of check points to set for the player.
     */
    public void setCheckPoints(int checkPoints) {
        this.checkPoints = checkPoints;
    }

    /**
     * Retrieves the array of the last played cards by the player.
     * <p>
     * This array contains up to four cards representing the most recent cards played by the player.
     *
     * @return An array of Card objects representing the last played cards.
     */
    public Card[] getLastPlayedCards() {
        return lastPlayedCards;
    }

    /**
     * Retrieves the number of statues collected by the player.
     *
     * @return The number of statues collected by the player.
     */
    public int getAmountOfStatues() {
        return amountOfStatues;
    }

    /**
     * Sets the number of statues collected by the player.
     *
     * @param amountOfStatues The number of statues to set for the player.
     */
    public void setAmountOfStatues(int amountOfStatues) {
        this.amountOfStatues = amountOfStatues;
    }



}
