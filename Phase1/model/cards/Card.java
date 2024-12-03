package Phase1.model.cards;
import javax.swing.*;
import java.awt.event.MouseListener;

public abstract class Card {
    private ImageIcon minImage;
    private String Palace;
    private MouseListener mouseListener;
    /**
     * <b>Constructor</b>: Initializes a Card Object
     * Class is an abstract class. Can only be used to create subclasses using super
     * @param minImage
     * @param palace
     */
    public Card(ImageIcon minImage, String palace) {
        this.minImage = minImage;
        this.Palace = palace;

    }

    /**
     *
     * @return Returns the image of the card.
     */
    public ImageIcon getImage() {
        return minImage;
    }

    /**
     * <b>Sets</b> the image of the card.
     * @param minImage
     */
    public void setImage(ImageIcon minImage) {
        this.minImage = minImage;
    }

    /**
     * Returns the palace's name
     * @return
     */
    public String getPalaceName() {
        return Palace;
    }

    /**
     * Sets the name of the corresponding palace
     * @param palace
     */
    public void setPalace(String palace) {
        Palace = palace;
    }

    /**
     * Returns the action listener.
     *
     * @return
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Sets the action listener.
     *
     * @param mouseListener
     */
    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }
}
