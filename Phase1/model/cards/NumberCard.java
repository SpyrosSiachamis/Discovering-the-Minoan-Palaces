package Phase1.model.cards;

import javax.swing.*;

public class NumberCard extends Card{
    private final int number;
    private ImageIcon image;

    /**
     * <b>Constructor</b>: Initializes a NumberCard Card Class.
     * It inherits the constructor of the Card class and also adds a number
     * @param Image Image of the Card.
     * @param palace Name of the corresponding Palace.
     * @param number
     */
    public NumberCard(ImageIcon Image, String palace, int number) {
        super(Image, palace);
        this.number = number;
    }

    /**
     * Retrieves the points associated with the NumberCard.
     *
     * @return The number of points corresponding to the NumberCard.
     */
    public int getPoints(){
        return number;
    }
}
