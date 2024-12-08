package src.model.findings;

import javax.swing.*;

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
}
