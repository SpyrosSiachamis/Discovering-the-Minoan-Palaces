package src.model.findings;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a Fresco, which is a type of Archaeological Finding extending the Finding class.
 */
public class Fresco extends Finding{
    /**
     * <b>Constructor</b>: Constructs a new `Fresco` object.
     *
     * Initializes a `Fresco` Finding object with given points value, finding image and finding name.
     * Inherits properties from its superclass `Finding`.
     * Precondition: `points` input value must be a non-negative integer,
     *               `findingImage` input is a non-null ImageIcon object,
     *               `findingName` input is a non-null and non-empty string.
     *
     * @param points       An integer representing the points value of this Fresco card.
     * @param findingImage An ImageIcon representing the image of archaeological finding that is Fresco.
     * @param findingName  A String representing the name of the Fresco.
     */
    public Fresco(int points, ImageIcon findingImage, String findingName) {
        super(points, findingImage, findingName);
    }

    /**
     * Retrieves a scaled version of the finding image as a JLabel.
     * <p>
     * This method scales the image of the finding by one-third of its original dimensions and returns a JLabel
     * containing the scaled image for display.
     * <p>
     * <b>Pre-condition</b>: The image returned by {@link #getFindingImage()} must not be null.
     * <p>
     * <b>Post-condition</b>: A JLabel containing the scaled image is returned, which can be used in the UI.
     *
     * @return A JLabel containing the scaled image of the finding.
     */
    public JLabel getFrescoImage() {
        JLabel frescoImage = new JLabel();
        int x;
        int y;
        x = getFindingImage().getImage().getWidth(null);
        y = getFindingImage().getImage().getHeight(null);
        Image scaledImage = getFindingImage().getImage().getScaledInstance(x/3, y/3, Image.SCALE_SMOOTH);
        frescoImage.setIcon(new ImageIcon(scaledImage));
        return frescoImage;
    }
}
