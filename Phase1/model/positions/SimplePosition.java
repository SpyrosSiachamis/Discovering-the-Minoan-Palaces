package Phase1.model.positions;

import javax.swing.*;

public class SimplePosition implements Position {
    private int points;
    private ImageIcon image;

    /**
     * Creates a new simple position object.
     * @param image Image of the SimplePosition;
     * @param points Points of the SimplePosition
     */
    public SimplePosition(ImageIcon image ,int points) {
        this.points = points;
        this.image = image;
    }

    /**
     * Returns the points of the SimplePosition.
     * @return Points of SimplePosition.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the SimplePosition.
     * @param points of SimplePosition.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Returns the image of the SimplePosition.
     * @return Image of SimplePosition.
     */
    public ImageIcon getPositionImage() {
        return image;
    }

    /**
     * Sets the image of the SimplePosition.
     * @param positionImage
     */
    public void setPositionImage(ImageIcon positionImage) {
        this.image = positionImage;
    }
}
