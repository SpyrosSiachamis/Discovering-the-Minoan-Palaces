package src.model.cards;

import javax.swing.*;

/**
 * Represents a SpecialCard, which is an abstract class extending Card.
 */
public abstract class SpecialCard extends Card {
    /**
     * <b>Constructor</b>: Initializes a SpecialCard Object.
     * @param Image Takes an image object. It is created using images from the Assets package
     * @param palace Name of the corresponding Palace that the card belongs to.
     */
    public SpecialCard(ImageIcon Image, String palace) {
        super(Image, palace);
    }
}
