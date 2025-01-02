package src.model.positions;

import src.model.pawns.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing the Path object defined for a palace.
 * Each Path object consists of a list of Positions and an associated Path name.
 *<p>
 * Invariants:
 * <p>
 * - Positions in a Path are never null.
 * <p>
 * - Path name is never null or blank.
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
     * Adds a Pawn object to the current Path.
     *
     * @param pawn The Pawn object to be added to the Path.
     */
    public void addPawn(Pawn pawn){}

    public boolean HasPawn1() {
        return hasPawn1;
    }

    public void setHasPawn1(boolean hasPawn1) {
        this.hasPawn1 = hasPawn1;
    }

    public boolean HasPawn2() {
        return hasPawn2;
    }

    public void setHasPawn2(boolean hasPawn2) {
        this.hasPawn2 = hasPawn2;
    }
}
