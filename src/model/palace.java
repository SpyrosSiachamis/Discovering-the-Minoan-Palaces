package src.model;
import src.model.cards.*;
import src.model.positions.*;
import javax.swing.*;
import java.util.ArrayList;

public class palace {
    private int totalCards = 0;
    String name;
    private final Path path;
    private final ArrayList<NumberCard> numCards = new ArrayList<NumberCard>();
    private final ArrayList<SpecialCard> spCards = new ArrayList<SpecialCard>();

    /**
     * Constructs a new Palace object with a specified name and associated path.
     * Initializes the card sets for the palace, including number cards and special cards.
     * Loads the images for each type of card and constructs the card objects.
     *
     * @param name The name of the palace being constructed.
     * @param path The Path object associated with the palace.
     */
    public palace(String name, Path path) {
        this.totalCards = 0;
        this.name = name;
        this.path = path;

        ImageIcon minImage = new ImageIcon("src/assets/images/cards/" + name+"Min"+".jpg");
        ImageIcon ariImage = new ImageIcon("src/assets/images/cards/" + name+"Ari"+".jpg");

        for(int i =0; i<10; i++){
            ImageIcon im = new ImageIcon("src/assets/images/cards/" +name+(i+1)+".jpg");
            numCards.add(new NumberCard(im,name,i+1));
            totalCards++;
            numCards.add(new NumberCard(im,name,i+1));
            totalCards++;
        }
        for(int i =0; i<3; i++){
            spCards.add(new Ariadne(ariImage,name));
            totalCards++;
        }
        for(int i =0; i<2; i++){
            spCards.add(new Minotaur(minImage,name));
            totalCards++;
        }

        System.out.println("Cards of " + name +": "+ totalCards);
        System.out.println("Number Cards of " + name +": "+ numCards.size());
        System.out.println("Special Cards of " + name +": "+ spCards.size());
    }

    /**
     * Retrieves the list of number cards associated with the palace.
     *
     * @return An ArrayList of NumberCard objects representing the number cards of the palace.
     */
    public ArrayList<NumberCard> getNumCards() {
        return numCards;
    }

    /**
     * Retrieves the list of special cards associated with the palace.
     *
     * @return An ArrayList of SpecialCard objects representing the special cards.
     */
    public ArrayList<SpecialCard> getSpCards() {
        return spCards;
    }

    /**
     * Returns the Path object associated with this palace.
     *
     * @return the Path object associated with this palace.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Retrieves the name associated with the palace.
     *
     * @return the name of the palace as a String.
     */
    public String getName(){
        return name;
    }
}
