package Phase1.model.pawns;

import Phase1.model.positions.Path;
import Phase1.model.positions.Position;

import java.awt.*;

public abstract class Pawn {
    private Position position;
    private Image image;

    /**
     * Constructs a new Pawn object with the given Position and Image.
     *
     * @param position The Position object representing the current position of the Pawn.
     * @param image The Image object representing the visual representation of the Pawn.
     */
    public Pawn(Position position, Image image) {
        this.position = position;
        this.image = image;
    }



    /**
     * Retrieves the current Position object associated with this Pawn.
     *
     * @return the Position object representing the current position of the Pawn.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the Position object for the current Pawn.
     *
     * @param position The Position object to set for the current Pawn.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets the Image object representing the visual representation of the Pawn.
     *
     * @return the Image object of the Pawn.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the Image object for a Pawn.
     *
     * @param image The Image object to be set for the Pawn.
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
