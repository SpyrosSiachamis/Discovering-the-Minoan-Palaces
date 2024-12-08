package src.model.positions;

public abstract class Position {
    private int x;
    private String path;
    private int points;

    /**
     * Represents a position with an x-coordinate and a path associated with it.
     *
     * @param x The x-coordinate of the Position.
     * @param path The Path name associated with the Position.
     */
    public Position(int x, String path) {
        this.x = x;
        this.path = path;
        this.points = 0;
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
}
