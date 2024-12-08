package Phase1.model;

import Phase1.model.cards.Card;
import Phase1.model.findings.Finding;
import Phase1.model.pawns.Arch;
import Phase1.model.pawns.Pawn;
import Phase1.model.pawns.Thesseus;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private String Name;
    private int points;
    private ArrayList<Finding> finds;
    private ArrayList<Card> cards;
    private ArrayList<Pawn> pawns;
    private boolean hasWon = false;
    private File music;
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
     * @return Returns the total amount of points of Player.
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
     * @return Returns the Archaeological Finds of the player.
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
     *
     * @return Returns the cards of the Player.
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
     * @return Pawns of Player.
     */
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }
    public int amountOfArchaiologists(){
        int size=0;
        for (int i =0; i<pawns.size(); i++){
            if (pawns.get(i) instanceof Arch){
                size++;
            }
        }
        return size;
    }

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
     *
     * @return Returns the music of the Player.
     */
    public File getMusic() {
        return music;
    }

    /**
     *
     * @param music Sets the music of the Player.
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
}
