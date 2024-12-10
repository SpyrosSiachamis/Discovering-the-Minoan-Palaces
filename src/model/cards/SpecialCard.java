package src.model.cards;

import javax.swing.*;

/**
 * Represents a SpecialCard, which is an abstract class extending Card.
 */
public abstract class SpecialCard extends Card {

    /**
     * <b>Constructor</b>: Constructs a new `Card` object.
     * <p>
     * Initializes the `Card` with a provided ImageIcon and palace name string.
     * <p>
     * Precondition: `minImage` input should be a non-null ImageIcon. `palace` must be a valid non-empty String.
     *
     * @param minImage The ImageIcon representing the image of the card.
     * @param palace   The name of the palace that the card is associated with.
     */
    public SpecialCard(ImageIcon minImage, String palace) {
        super(minImage, palace);
    }
}
