package src.model.positions;

public class SimplePosition extends Position {
    private Square square;

    /**
     * Constructs a new SimplePosition object with the provided x-coordinate, Path, points, and square.
     *
     * @param x The x-coordinate of the SimplePosition.
     * @param path The Path name associated with the SimplePosition.
     * @param square The Square object associated with the SimplePosition.
     */
    public SimplePosition(int x, String path, Square square) {
        super(x, path);
        this.square = square;
    }


    /**
     * Retrieves the Square object associated with this SimplePosition.
     *
     * @return the Square object associated with this SimplePosition.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Sets the Square object associated with this SimplePosition.
     *
     * @param square The Square object to be set for this SimplePosition.
     */
    public void setSquare(Square square) {
        this.square = square;
    }
}
