package src.model.cards;

import javax.swing.*;

/**
 * Represents a {@code SpecialCard}, an abstract subclass of {@link Card}.
 * <p>
 * This class provides a foundation for specific types of special cards in the game.
 * <p>
 * <b>Post-condition:</b> A new {@link SpecialCard} is initialized with an image and palace name.
 */
public abstract class SpecialCard extends Card {

    /**
     * <b>Constructor</b>: Creates a new {@link SpecialCard} object.
     * <p>
     * Initializes the special card with a given image and palace name.
     * This class extends {@link Card}, and thus inherits the card's functionality.
     * <p>
     * <b>Pre-condition:</b>
     * <ul>
     *     <li>`minImage` should be a non-null {@link ImageIcon} representing the image of the card.</li>
     *     <li>`palace` must be a valid, non-empty {@link String} representing the name of the associated palace.</li>
     * </ul>
     * <p>
     * <b>Post-condition:</b> A new {@link SpecialCard} is created with the provided image and palace name.
     *
     * @param minImage The {@link ImageIcon} representing the image of the special card.
     * @param palace   The name of the palace associated with this card.
     */
    public SpecialCard(ImageIcon minImage, String palace) {
        super(minImage, palace);
    }
}
