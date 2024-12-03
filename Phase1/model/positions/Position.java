package Phase1.model.positions;
import javax.swing.*;
public abstract interface Position {
    /**
     * Returns the amount of points of the position.
     * @return Points
     */
    int getPoints();

    /**
     *
     * @param points
     */
    void setPoints(int points);

    ImageIcon getPositionImage();

    void setPositionImage(ImageIcon positionImage);
}
