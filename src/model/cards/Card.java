package src.model.cards;
import javax.swing.*;
import java.awt.*;

/**
 * Represents a card in the game.
 * Each card is associated with an image and a palace, and it provides methods to access and modify the card's properties.
 * <p>
 * <b>Invariants:</b>
 * <ul>
 *     <li>The card image is represented by a valid {@link ImageIcon}.</li>
 *     <li>The palace name is a valid, non-empty string.</li>
 *     <li>The card button is initialized with a scaled image of the card.</li>
 * </ul>
 */
public abstract class Card {
    private ImageIcon minImage;
    private String Palace;
    private JButton cardButton;
    private ImageIcon backCard = new ImageIcon("src/assets/images/cards/backCard.jpg");

    /**
     * <b>Constructor</b>: Constructs a new `Card` object.
     * <p>
     * Initializes the `Card` with a provided ImageIcon and palace name string.
     * <p>
     * <b>Pre-condition:</b>
     * <ul>
     *     <li>`minImage` input should be a non-null {@link ImageIcon}.</li>
     *     <li>`palace` must be a valid, non-empty {@link String}.</li>
     * </ul>
     * <p>
     * <b>Post-condition:</b> The `Card` is initialized with a scaled image and a palace name.
     *
     * @param minImage The ImageIcon representing the image of the card.
     * @param palace The name of the palace that the card is associated with.
     */
    public Card(ImageIcon minImage, String palace) {
        this.minImage = minImage;
        this.Palace = palace;
        ImageIcon originalIcon = minImage;
        Image scaledImage = originalIcon.getImage().getScaledInstance(67,95,Image.SCALE_SMOOTH);
        cardButton = new JButton(new ImageIcon(scaledImage));
        cardButton.setBorderPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.setFocusPainted(true);
    }

    /**
     * Retrieves the {@link ImageIcon} representing the card.
     * <p>
     * <b>Post-condition:</b> A scaled version of the card's image is returned.
     *
     * @return The ImageIcon representing the image of the card.
     */
    public ImageIcon getImage() {
        ImageIcon originalIcon = minImage;
        Image scaled = originalIcon.getImage().getScaledInstance(75,95,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Sets the {@link ImageIcon} representing the image of the card.
     * <p>
     * <b>Pre-condition:</b> `minImage` input should be a non-null {@link ImageIcon}.
     * <p>
     * <b>Post-condition:</b> The image of the card is updated with the new {@link ImageIcon}.
     *
     * @param minImage The new ImageIcon to set for the card's image.
     */
    public void setImage(ImageIcon minImage) {

        this.minImage = minImage;
    }

    /**
     * Retrieves the name of the palace associated with the card.
     * <p>
     * <b>Post-condition:</b> The name of the palace associated with this card is returned.
     *
     * @return The name of the palace associated with the card.
     */
    public String getPalaceName() {
        return Palace;
    }

    /**
     * Retrieves the {@link JButton} representing the clickable button for this card.
     * <p>
     * <b>Post-condition:</b> The {@link JButton} associated with the card is returned.
     *
     * @return The JButton representing the card.
     */
    public JButton getCardButton() {
        return cardButton;
    }

}
