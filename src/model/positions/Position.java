package src.model.positions;

/**
 * The {@code Position} class represents a specific position on a path in the game.
 * Each position has an associated x-coordinate, a path name, points, and a square.
 * <p>
 * The Position class is abstract and serves as a base for other position-related classes.
 * <p>
 * <b>Pre-condition</b>: A valid integer value for the x-coordinate, a non-null string for the path name,
 * and a non-null {@link Square} object must be provided when constructing a position.
 * <p>
 * <b>Post-condition</b>: The position is initialized with the given x-coordinate, path name, and square.
 * The points are initially set to 0.
 * <p>
 * <b>Invariant</b>: A Position object has a defined x-coordinate, path, square, and points.
 */
public abstract class Position {
    private int x;
    private String path;
    private int points;
    private Square square;
    /**
     * Represents a position with an x-coordinate and a path associated with it.
     *
     * @param x The x-coordinate of the Position.
     * @param path The Path name associated with the Position.
     * @param square The Square object associated with this Position.
     */
    public Position(int x, String path, Square square) {
        this.x = x;
        this.path = path;
        this.points = 0;
        this.square = square;
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

    /**
     * Retrieves the Square associated with this Position.
     * <p>
     * Postcondition: Returns the Square object currently assigned to this Position.
     *
     * @return The Square object associated with this Position.
     */
    public Square getSquare() {
        return square;
    }
}
