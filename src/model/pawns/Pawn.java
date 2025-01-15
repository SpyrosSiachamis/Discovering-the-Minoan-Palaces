package src.model.pawns;

import src.model.positions.Position;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code Pawn} class represents a generic pawn in the game. It contains information about the pawn's position,
 * image, and various attributes, such as whether its power is activated, and the ability to hide or show the pawn on the board.
 * <p>
 * <b>Invariants:</b>
 * <ul>
 *   <li>The pawn always has an associated position.</li>
 *   <li>The pawn has an image associated with its visual representation, which can be set or retrieved.</li>
 *   <li>The pawn can be hidden or shown on the board by changing its icon.</li>
 *   <li>Can set a boolean value to indicate if the pawn has used its specific power, revealing itself to the other player.</li>
 * </ul>
 */
public abstract class Pawn {
    private Position position;
    ImageIcon image;
    int xPos;
    JLabel pawn = new JLabel();
    Image scaledImage;
    ImageIcon question = new ImageIcon("src/assets/images/pionia/question.jpg");
    Image scaledQuestion = question.getImage().getScaledInstance(30,50,Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon();
    int x,y;
    ImageIcon questionIcon = new ImageIcon();
    String palace;
    GridBagConstraints location;
    private boolean powerActivated = false;
    /**
     * Constructs a new {@code Pawn} object.
     * <p>
     * This constructor initializes a generic pawn, but does not set any specific image or position.
     * <p>
     * Precondition: The position and image of the pawn will need to be set before use.
     */
    public Pawn() {
    }



    /**
     * Retrieves the current {@link Position} object associated with this Pawn.
     * <p>
     * Postcondition: Returns the {@link Position} representing the current position of the Pawn.
     *
     * @return the {@link Position} object representing the current position of the Pawn.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the {@link Position} object for the current Pawn.
     * <p>
     * Postcondition: The position of this pawn is updated to the provided position.
     *
     * @param position The {@link Position} object to set for the current Pawn.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets the {@link Image} object representing the visual representation of the Pawn.
     * <p>
     * Postcondition: Returns the {@link Image} object associated with the Pawn.
     *
     * @return the {@link Image} object of the Pawn.
     */
    public Image getImage() {
        Image im = image.getImage();
        return im;
    }

    /**
     * Sets the {@link ImageIcon} for the Pawn.
     * <p>
     * Postcondition: The image of this pawn is updated to the provided {@link ImageIcon}.
     *
     * @param image The {@link ImageIcon} to be set for the Pawn.
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Retrieves the {@link JLabel} representing the Pawn's visual component.
     * <p>
     * Postcondition: Returns the {@link JLabel} object associated with the Pawn.
     *
     * @return the {@link JLabel} representing the Pawn.
     */
    public JLabel getPawn() {
        return pawn;
    }

    /**
     * Retrieves the x-coordinate of the Pawn.
     * <p>
     * Postcondition: Returns the x-coordinate of the Pawn.
     *
     * @return the x-coordinate of the Pawn.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the Pawn.
     * <p>
     * Postcondition: The x-coordinate of the Pawn is updated.
     *
     * @param x The x-coordinate to set for the Pawn.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the y-coordinate of the Pawn.
     * <p>
     * Postcondition: Returns the y-coordinate of the Pawn.
     *
     * @return the y-coordinate of the Pawn.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the Pawn.
     * <p>
     * Postcondition: The y-coordinate of the Pawn is updated.
     *
     * @param y The y-coordinate to set for the Pawn.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieves the palace name associated with the Pawn.
     * <p>
     * Postcondition: Returns the name of the palace associated with this Pawn.
     *
     * @return the name of the palace associated with the Pawn.
     */
    public String getPalace() {
        return palace;
    }

    /**
     * Sets the palace name associated with the Pawn.
     * <p>
     * Postcondition: The name of the palace is set for this Pawn.
     *
     * @param palace The name of the palace to set for the Pawn.
     */
    public void setPalace(String palace) {
        this.palace = palace;
    }

    /**
     * Hides the Pawn by setting its icon to a question mark image.
     * <p>
     * Postcondition: The Pawn is visually hidden by displaying a question mark image.
     */
    public void hidePawn() {
        questionIcon = new ImageIcon(scaledQuestion);
        pawn.setIcon(questionIcon);
    }

    /**
     * Reveals the Pawn by setting its icon to the scaled image associated with it.
     * <p>
     * Postcondition: The Pawn is visually shown with its original image.
     */
    public void showPawn() {
        scaledIcon = new ImageIcon(scaledImage);
        pawn.setIcon(scaledIcon);
    }

    /**
     * Retrieves whether the Pawn's power has been used once.
     * <p>
     * Postcondition: Returns whether the power of the Pawn has been used once.
     *
     * @return {@code true} if the power has been used, otherwise {@code false}.
     */
    public boolean isPowerActivated() {
        return powerActivated;
    }

    /**
     * Sets the activation state of the Pawn's power.
     * <p>
     * Postcondition: The activation state of the Pawn's power is updated.
     *
     * @param powerActivated {@code true} to indicate that the power has been used, {@code false} to indicate the opposite.
     */
    public void setPowerActivated(boolean powerActivated) {
        this.powerActivated = powerActivated;
    }
}
