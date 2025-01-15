package src.model.positions;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing a path object for a palace. Each path consists of a list of positions and a corresponding name.
 * Each position within the path has associated points and an image. The path includes squares for positions, and may
 * contain special positions such as a palace square.
 * <p>
 * <b>Invariants:</b>
 * <ul>
 *   <li>Positions within the path are never null.</li>
 *   <li>The path name is never null or blank.</li>
 * </ul>
 */
public class Path{
    private ArrayList<Position> path = new ArrayList<Position>();
    private String pathName;
    boolean hasPawn1 = false;
    boolean hasPawn2 = false;
    /**
     * <b>Constructor</b>: Constructs a new Path Object.
     * <p>
     * The constructor initializes the path with varying points and a name corresponding to the given palace.
     * <p>
     * Loop invariant: For each position in the path, the point's value increases by 5 with except for certain positions.
     * <p>
     * Postcondition: A path is constructed with 9 Positions.
     *<p>
     * @param pathName The name of the path, corresponding to the palace it belongs to.
     * <p>
     * Precondition: Provided pathName must not be null or empty.
     */
    public Path(String pathName) {
            Image scaledImage;
            this.pathName = pathName;
            int points = -20;
            for (int i=0; i<9; i++){
                if (points==-5){
                    points = 5;
                }
                if(points ==20){
                    points = 30;
                }
                if ((i+1) % 2 == 1){
                    ImageIcon ogIm = new ImageIcon("src/assets/images/paths/"+pathName+".jpg");
                    scaledImage = ogIm.getImage().getScaledInstance(95,80,Image.SCALE_SMOOTH);
                    Square pathSquare = new Square(new ImageIcon(scaledImage));
                    path.add(new SimplePosition(i,pathName,pathSquare));
                }
                if ((i+1)%2 == 0){
                    ImageIcon ogIm = new ImageIcon("src/assets/images/paths/"+pathName+"2.jpg");
                    scaledImage = ogIm.getImage().getScaledInstance(95,80,Image.SCALE_SMOOTH);
                    Square pathSquare = new Square(new ImageIcon(scaledImage));
                    path.add(new FindingPosition(i,pathName,pathSquare));
                }
                path.get(i).setPoints(points);
                points+=5;
                if (i==8){
                    points = 50;
                    ImageIcon ogIm = new ImageIcon("src/assets/images/paths/"+pathName+"Palace.jpg");
                    scaledImage = ogIm.getImage().getScaledInstance(123,95,Image.SCALE_SMOOTH);
                    Square palaceSquare = new Square(new ImageIcon(scaledImage));
                    FindingPosition palace = new FindingPosition(i,pathName,palaceSquare);
                    palace.setPoints(points);
                    path.add(palace);
                }
            }
            path.remove(8);

    }

    /**
     * Returns the ArrayList of {@code Position} objects comprising the Path.
     * <p>
     * Post-condition: The ArrayList of path positions is returned.
     *<p>
     * @return the list of {@code Position} objects of this Path.
     */
    public ArrayList<Position> getPositions() {
        return path;
    }

    /**
     * Checks if a pawn of player1 is present on this path.
     * <p>
     * Postcondition: Returns {@code true} if a pawn of player1 is on the path, otherwise returns {@code false}.
     *
     * @return A boolean indicating whether a pawn of  player1 is present on the path.
     */
    public boolean HasPawn1() {
        return hasPawn1;
    }

    /**
     * Sets the Pawn of player1 on this path.
     * <p>
     * Postcondition: The existance of a pawn of player1 is updated based on the provided boolean value.
     *
     * @param hasPawn1 A boolean indicating whether a pawn of player1 is present on the path.
     */
    public void setHasPawn1(boolean hasPawn1) {
        this.hasPawn1 = hasPawn1;
    }

    /**
     * Checks if a pawn of player2 is present on this path.
     * <p>
     * Postcondition: Returns {@code true} if a pawn of player2 is on the path, otherwise returns {@code false}.
     *
     * @return A boolean indicating whether a pawn of  player2 is present on the path.
     */
    public boolean HasPawn2() {
        return hasPawn2;
    }

    /**
     * Sets the Pawn of player2 on this path.
     * <p>
     * Postcondition: The existance of a pawn of player2 is updated based on the provided boolean value.
     *
     * @param hasPawn2 A boolean indicating whether a pawn of player2 is present on the path.
     */
    public void setHasPawn2(boolean hasPawn2) {
        this.hasPawn2 = hasPawn2;
    }
}
