package src.model.positions;

import src.model.pawns.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The {@code Square} class represents a square of a position of a path. Each square has an associated image
 * and can hold up to two pawns. It provides methods to retrieve and set the image of the square,
 * as well as methods for handling mouse events on the square.
 * <p>
 * <b>Pre-condition</b>: A valid {@link ImageIcon} object must be provided when constructing a square.
 * <p>
 * <b>Post-condition</b>: The square is initialized with an image, and a label is created for visual representation.
 * <p>
 * <b>Invariant</b>: The square can contain two pawns at most, and the mouse listener is set to respond to mouse events.
 */
public class Square{
    ImageIcon SquareImage; //The image of the Square object.
    public JLabel SquareLabel;
    private MouseListener mouseListener;
    Pawn pawn1;
    Pawn pawn2;

    /**
     * Constructs a new {@code Square} object.
     * <p>
     * Initializes the square with the specified {@link ImageIcon}, creates a label for visual representation,
     * and prepares the square to handle mouse events through the assigned listener.
     * <p>
     * <b>Pre-condition</b>: The {@code squareImage} parameter must be a valid, non-null {@link ImageIcon}.
     *
     * @param squareImage An {@link ImageIcon} representing the image of the Square.
     */
    public Square(ImageIcon squareImage) {
        SquareImage = squareImage;
        SquareLabel = new JLabel(SquareImage);
        SquareLabel.addMouseListener(mouseListener);
    }

    /**
     * Retrieves the {@link JLabel} representing the square.
     * <p>
     * Postcondition: Returns the {@link JLabel} that visually represents this square.
     *
     * @return The {@link JLabel} used to represent the square.
     */
    public JLabel getSquareLabel() {
        return SquareLabel;
    }

    /**
     * Sets the {@link MouseListener} for this square.
     * <p>
     * Postcondition: Adds the given mouse listener to the square's label to respond to mouse events.
     *
     * @param mouseListener The {@link MouseListener} to be added to the square's label.
     */
    public void setMouseListener(MouseListener mouseListener) {
        SquareLabel.addMouseListener(mouseListener);
    }

    /**
     * Retrieves the {@link Pawn} of player1 on this square.
     * <p>
     * Postcondition: Returns the {@link Pawn} of player1 located on the square, or {@code null} if none is present.
     *
     * @return The {@link Pawn} of player1 on the square.
     */
    public Pawn getPawn1() {
        return pawn1;
    }

    /**
     * Sets the {@link Pawn} of player1 on this square.
     * <p>
     * Postcondition: The {@link Pawn} of player1 is assigned to this square.
     *
     * @param pawn The {@link Pawn} of player1 to be placed on the square.
     */
    public void setPawn1(Pawn pawn) {
        this.pawn1 = pawn;
    }

    /**
     * Retrieves the {@link Pawn} of player2 on this square.
     * <p>
     * Postcondition: Returns the {@link Pawn} of player2 located on the square, or {@code null} if none is present.
     *
     * @return The {@link Pawn} of player2 on the square.
     */
    public Pawn getPawn2() {
        return pawn2;
    }

    /**
     * Sets the {@link Pawn} of player2 on this square.
     * <p>
     * Postcondition: The {@link Pawn} of player2 is assigned to this square.
     *
     * @param pawn2 The {@link Pawn} of player2 to be placed on the square.
     */
    public void setPawn2(Pawn pawn2) {
        this.pawn2 = pawn2;
    }
}
