package src.model.cards;

import javax.swing.*;

/**
 * Represents a Minotaur special card in the game.
 * <p>
 * The {@code Minotaur} card is a subclass of the {@link SpecialCard} class and inherits its properties and behaviors.
 * <p>
 * <b>Post-condition:</b> A new {@link Minotaur} card is created with the provided image and palace name.
 */
public class Minotaur extends SpecialCard {
    /**
     * <b>Constructor</b>: Creates a {@link Minotaur} card object.
     * <p>
     * This constructor initializes a Minotaur card with an image and palace name.
     * It is a subclass of the {@link SpecialCard} class, inheriting its properties and behaviors.
     * <p>
     * <b>Pre-condition:</b>
     * <ul>
     *     <li>`minImage` should be a non-null {@link ImageIcon} representing the card's image.</li>
     *     <li>`palace` should be a valid, non-empty {@link String} representing the associated palace.</li>
     * </ul>
     * <p>
     * <b>Post-condition:</b> A {@link Minotaur} card is initialized with the given image and palace name.
     *
     * @param minImage The {@link ImageIcon} representing the image of the Minotaur card.
     * @param palace The name of the palace associated with this Minotaur card.
     */
    public Minotaur(ImageIcon minImage, String palace) {
        super(minImage, palace);

    }
}
