package src.model.findings;

import javax.swing.*;

/**
 * Represents an Archaeological Finding object with points, image, and name.
 */
public class Finding {
    private int points;
    private ImageIcon findingImage;
    private String findingName;

    /**
     * Initializes a Finding Object with given points, finding image, and finding name.
     *
     * @param points       The points of the Archaeological Finding.
     * @param findingImage The ImageIcon representing the image of the Archaeological Finding.
     * @param findingName  The name of the Archaeological Finding.
     */
    public Finding(int points, ImageIcon findingImage, String findingName) {
        this.points = points;
        this.findingImage = findingImage;
        this.findingName = findingName;
    }

    /**
     * Retrieves the points of the Archaeological Finding.
     *
     * @return The points of the Archaeological Finding.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the total points of the finding.
     * @param points Total Value of points of the finding
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return Returns the Image of the Finding Object
     */
    public ImageIcon getFindingImage() {
        return findingImage;
    }

    /**
     * Retrieves the name of the Archaeological Finding.
     *
     * @return The name of the Archaeological Finding.
     */
    public String getFindingName() {
        return findingName;
    }

}
