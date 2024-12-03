package Phase1.model;

import Phase1.model.cards.Card;
import Phase1.model.findings.Finding;
import Phase1.model.pawns.Pawn;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;

public class Player {
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
    public Player(int points, ArrayList<Card> cards, ArrayList<Pawn> pawns, File music) {
        this.points = points;
        this.cards = cards;
        this.pawns = pawns;
        this.music = music;
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

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
