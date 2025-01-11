package src.model.positions;

import java.awt.event.MouseListener;

public abstract class Position {
    private int x;
    private String path;
    private int points;
    private Square square;
    private boolean hasPawn = false;
    /**
     * Represents a position with an x-coordinate and a path associated with it.
     *
     * @param x The x-coordinate of the Position.
     * @param path The Path name associated with the Position.
     */
    public Position(int x, String path, Square square) {
        this.x = x;
        this.path = path;
        this.points = 0;
        this.square = square;
    }

    public Position() {

    }

    /**
     * Returns the path associated with this Position object.
     *
     * @return the Path object associated with this Position.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the Path object for a Position.
     *
     * @param path The Path object to be set for the Position.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Retrieves the x-coordinate of the Position object.
     *
     * @return the x-coordinate of the Position object as an integer.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of this Position.
     *
     * @param x the new x-coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * Sets the points for the current Position.
     *
     * @param points The integer value of points to be set for the Position.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves the points associated with this Position object.
     *
     * @return the points associated with this Position as an integer.
     */
    public int getPoints() {
        return points;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }


    public boolean HasPawn() {
        return hasPawn;
    }

    public void setHasPawn(boolean hasPawn) {
        this.hasPawn = hasPawn;
    }
}
