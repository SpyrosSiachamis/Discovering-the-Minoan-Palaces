package src.model.cards;

import javax.swing.*;

public class Minotaur extends SpecialCard {

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
    public Minotaur(ImageIcon minImage, String palace) {
        super(minImage, palace);

    }
}
