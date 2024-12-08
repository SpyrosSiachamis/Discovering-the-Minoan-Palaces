package src.model.findings;

import javax.swing.*;

public class RareFinding extends Finding{
    /**
     * Constructs a RareFinding object with the specified points, finding image, and finding name.
     *
     * @param points The points associated with the RareFinding object.
     * @param findingImage The ImageIcon representing the image of the RareFinding.
     * @param findingName The name of the RareFinding.
     */
    public RareFinding(int points, ImageIcon findingImage, String findingName) {
        super(points, findingImage, findingName);
    }
}
