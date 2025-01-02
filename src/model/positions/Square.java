package src.model.positions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square{
    ImageIcon SquareImage; //The image of the Square object.
    public JLabel SquareLabel;
    private MouseListener mouseListener;

    public JLabel getSquareLabel() {
        return SquareLabel;
    }

    public void setSquareLabel(JLabel squareLabel) {
        SquareLabel = squareLabel;
    }

    /**
     * <b>Constructor</b>: Constructs a new `Square` object.
     *<p>
     * Initializes the `Square` with a given ImageIcon.
     * Precondition: `squareImage` input should be a non-null ImageIcon.
     * <p>
     * @param squareImage An ImageIcon representing the image of the Square.
     */
    public Square(ImageIcon squareImage) {
        SquareImage = squareImage;
        SquareLabel = new JLabel(SquareImage);
        SquareLabel.addMouseListener(mouseListener);
    }

    /**
     * Retrieves the ImageIcon of the Square.
     * <p>
     * Postcondition: The ImageIcon associated with this Square is returned.
     * <p>
     * @return The ImageIcon of the Square.
     */
    public ImageIcon getSquareImage() {
        return SquareImage;
    }

    /**
     * Sets the ImageIcon of the Square.
     * <p>
     * Postcondition: The ImageIcon of this Square is set to `positionImage`.
     * <p>
     * Precondition: `positionImage` should be a non-null ImageIcon.
     * <p>
     * @param positionImage An ImageIcon representing the new image of the Square.
     */
    public void setSquareImage(ImageIcon positionImage) {
        this.SquareImage = positionImage;
    }
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        SquareLabel.addMouseListener(mouseListener);
    }
}
