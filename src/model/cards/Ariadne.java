package src.model.cards;

import javax.swing.*;

/**
 * Represents an Ariadne special card in the game.
 * <p>
 * The {@code Ariadne} card is a subclass of the {@link SpecialCard} class and inherits its properties and behaviors.
 * <p>
 * <b>Post-condition:</b> A new {@link Ariadne} card is created with the provided image and palace name.
 */
public class Ariadne extends SpecialCard{
    /**
     * <b>Constructor</b>: Creates an {@link Ariadne} card object.
     * <p>
     * This constructor initializes an Ariadne card with an image and palace name.
     * It is a subclass of the {@link SpecialCard} class, inheriting its properties and behaviors.
     * <p>
     * <b>Pre-condition:</b>
     * <ul>
     *     <li>`Image` should be a non-null {@link ImageIcon} representing the card's image.</li>
     *     <li>`palace` should be a valid, non-empty {@link String} representing the associated palace.</li>
     * </ul>
     * <p>
     * <b>Post-condition:</b> An Ariadne card is initialized with the given image and palace name.
     *
     * @param Image The {@link ImageIcon} representing the image of the Ariadne card.
     * @param palace The name of the palace associated with this Ariadne card.
     */
    public Ariadne(ImageIcon Image, String palace) {
        super(Image, palace);
    }
}
