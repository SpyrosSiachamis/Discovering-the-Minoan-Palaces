package Phase1.model.findings;

import javax.swing.*;

public abstract class Finding {
    private int points;
    private ImageIcon findingImage;

    /**
     * <b>Constructor</b>: Initializes a Finding Object.
     * @param points  Value of points of the Card.
     * @param findingImage Image of the Archaeological Finding.
     */
    public Finding(int points, ImageIcon findingImage) {
        this.points = points;
        this.findingImage = findingImage;
    }

    /**
     *
     * @return Returns the total points of the card.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the total points of the card.
     * @param points Total Value of points of the card
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
     * Sets the image of the finding object.
     * @param findingImage Image of the Finding Object
     */
    public void setFindingImage(ImageIcon findingImage) {
        this.findingImage = findingImage;
    }
}
