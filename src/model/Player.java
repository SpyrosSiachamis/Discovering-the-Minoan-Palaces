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
    private ArrayList<Finding> finds;
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
     * Sets the Archaeological Finds of Player.
     * @param finds Archaeological Findings.
     */
    public void setFinds(ArrayList<Finding> finds) {
        this.finds = finds;
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
                return 1;
            }
        }
        return 0;
    }

    /**
     * Sets the pawns of the Player.
     * @param pawns Pawns of Player.
     */
    public void setPawns(ArrayList<Pawn> pawns) {
        this.pawns = pawns;
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
     * Sets the music file associated with the player.
     *
     * @param music A File object representing the music file to be set for the player.
     */
    public void setMusic(File music) {
        this.music = music;
    }

    /**
     * Returns the Victory status of the player.
     * @return Victory Status
     */
    public boolean getVictoryStatus(){
        return hasWon;
    }

    /**
     * Sets the victory status of the player.
     *
     * @param hasWon A boolean representing the victory status to be set.
     */
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
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

    public int getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(int checkPoints) {
        this.checkPoints = checkPoints;
    }
    public Card[] getLastPlayedCards() {
        return lastPlayedCards;
    }

    public void setLastPlayedCards(Card[] lastPlayedCards) {
        this.lastPlayedCards = lastPlayedCards;
    }

}
