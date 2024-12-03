package Phase1.model;
import Phase1.model.cards.*;
import Phase1.model.positions.*;
import javax.swing.*;
import java.util.ArrayList;

public class palace {
    private int totalCards = 0;
    String name;
    private final Path path;
    private final ArrayList<NumberCard> numCards = new ArrayList<NumberCard>();
    private final ArrayList<SpecialCard> spCards = new ArrayList<SpecialCard>();

    /**
     *
     * @param name Name of the palace.
     * @param path Path of the palace.
     */
    public palace(String name, Path path) {
        this.totalCards = 0;
        this.name = name;
        this.path = path;

        ImageIcon minImage = new ImageIcon("Phase1/assets/images/cards/"+ name+"Min"+".jpg");
        ImageIcon ariImage = new ImageIcon("Phase1/assets/images/cards/"+ name+"Ari"+".jpg");

        for(int i =0; i<10; i++){
            ImageIcon im = new ImageIcon("Phase1/assets/images/cards/"+name+(i+1)+".jpg");
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

        System.out.println(totalCards);
    }

    /**
     *
     * @return Returns the ArrayList of the Number Cards of the Palace.
     */
    public ArrayList<NumberCard> getNumCards() {
        return numCards;
    }

    /**

     * @return Returns the ArrayList of the Special Cards of the Palace.
     */
    public ArrayList<SpecialCard> getSpCards() {
        return spCards;
    }

    /**
     *
     * @return Returns the path of the Palace.
     */
    public Path getPath() {
        return path;
    }
}
