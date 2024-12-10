package src.model.cards;
import javax.swing.*;
import java.awt.event.MouseListener;

public abstract class Card {
    private ImageIcon minImage;
    private String Palace;
    private JButton cardButton;

    /**
     * <b>Constructor</b>: Constructs a new `Card` object.
     * <p>
     * Initializes the `Card` with a provided ImageIcon and palace name string.
     * <p>
     * Precondition: `minImage` input should be a non-null ImageIcon. `palace` must be a valid non-empty String.
     *
     * @param minImage The ImageIcon representing the image of the card.
     * @param palace The name of the palace that the card is associated with.
     */
    public Card(ImageIcon minImage, String palace) {
        this.minImage = minImage;
        this.Palace = palace;
    }

    /**
     * Retrieves the ImageIcon representing the card.
     *
     * @return The ImageIcon representing the image of the card.
     */
    public ImageIcon getImage() {
        return minImage;
    }

    /**
     * Sets the ImageIcon representing the image of the card.
     *
     * @param minImage An ImageIcon representing the new image of the card.
     * <p>
     * Precondition: `minImage` input should be a non-null ImageIcon.
     */
    public void setImage(ImageIcon minImage) {
        this.minImage = minImage;
    }

    /**
     * Retrieves the name of the palace associated with the card.
     * <p>
     * @return A string representing the name of the associated palace.
     */
    public String getPalaceName() {
        return Palace;
    }

    /**
     * Sets the name of the palace that the card is associated with.
     *
     * @param palace A string representing the new associated palace name.
     * <p>
     * Precondition: `palace` input is a valid non-empty String.
     */
    public void setPalace(String palace) {
        Palace = palace;
    }



    @Override
    public String toString() {
        return "Card [Palace=" + Palace + ", Image=" + minImage + "]";
    }
}
