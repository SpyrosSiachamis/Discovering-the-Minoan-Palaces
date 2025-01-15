package src.model.positions;
public class SimplePosition extends Position {
    /**
     * Constructs a new SimplePosition object with the provided x-coordinate, Path, points, and square.
     *
     * @param x The x-coordinate of the SimplePosition.
     * @param path The Path name associated with the SimplePosition.
     * @param square The Square object associated with the SimplePosition.
     */
    public SimplePosition(int x, String path, Square square) {
        super(x, path, square);
    }
}
