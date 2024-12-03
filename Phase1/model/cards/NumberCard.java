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
     * @return Returns the value of the card.
     */
    public int getPoints(){
        return number;
    }

    /**
     * Takes a card object and checks if it matches with the card rules of the board game.
     * If the number of the card is larger/equals the card that it is matched with it returns true
     * else return false
     * @param c Takes a card object to compare.
     * @return True if card number is bigger/equal to the card its compared with, else return false
     */
    boolean matchCard(Card c){
        if(c instanceof NumberCard){
            return number >= ((NumberCard) c).number;
        }
        return false;
    }


}
