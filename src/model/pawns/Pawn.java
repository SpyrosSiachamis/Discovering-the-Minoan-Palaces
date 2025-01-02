package src.model.pawns;

import src.model.positions.Position;

import javax.swing.*;
import java.awt.*;

public abstract class Pawn {
    private Position position;
    private ImageIcon image;
    private JLabel pawn;
    GridBagConstraints location;
    /**
     * Constructs a new Pawn object with the given Position and Image.
     *
     * @param position The Position object representing the current position of the Pawn.
     */
    public Pawn() {
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
        Image im = image.getImage();
        return im;
    }

    /**
     * Sets the Image object for a Pawn.
     *
     * @param image The Image object to be set for the Pawn.
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public JLabel getPawn() {
        return pawn;
    }

    public void setPawn(JLabel pawn) {
        this.pawn = pawn;
    }
    public void setPawnImage(ImageIcon image) {
        pawn.setIcon(image);
    }
}
