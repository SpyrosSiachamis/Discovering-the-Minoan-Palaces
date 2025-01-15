package src.model.pawns;

import src.model.Player;
import src.model.cards.Minotaur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The {@code Thesseus} class represents a specialized pawn with unique attributes,
 * including tracking the number of findings it has destroyed and whether it has been attacked by the enemy player.
 * <p>
 * <b>Invariants:</b>
 * <ul>
 *   <li>The pawn always has an associated image representing its visual component.</li>
 *   <li>The pawn can have a border color depending on the associated player.</li>
 *   <li>The pawn tracks the number of findings it has destroyed.</li>
 *   <li>The pawn tracks whether it has been attacked.</li>
 * </ul>
 */
public class Thesseus extends Pawn {
    private int findingsDestroyed = 0;
    private boolean hasBeenAttacked = false;

    /**
     * Constructs a new {@code Thesseus} pawn with the specified image and player.
     * <p>
     * Precondition: The image and player must not be null.
     * <p>
     * Postcondition: A new {@code Thesseus} pawn is created with a border color specific to the player.
     *
     * @param image The {@link ImageIcon} representing the visual appearance of the Thesseus pawn.
     * @param player The {@link Player} associated with this pawn, used to determine the border color.
     */
    public Thesseus(ImageIcon image, Player player) {
        scaledImage = image.getImage().getScaledInstance(30,50,Image.SCALE_SMOOTH);
        pawn = new JLabel(new ImageIcon(scaledImage));
        if (player.getName().equals("1")) {
            pawn.setBorder(new LineBorder(Color.RED));
        }
        else if (player.getName().equals("2")) {
            pawn.setBorder(new LineBorder(Color.GREEN));
        }
    }

    /**
     * Retrieves the {@link JLabel} representing the visual component of the Thesseus pawn.
     * <p>
     * Postcondition: Returns the {@link JLabel} for the Thesseus pawn, which contains the visual representation.
     *
     * @return the {@link JLabel} representing the Thesseus pawn.
     */
    @Override
    public JLabel getPawn() {
        return pawn;
    }

    /**
     * Retrieves the number of findings destroyed by this Thesseus pawn.
     * <p>
     * Postcondition: Returns the number of findings destroyed.
     *
     * @return the number of findings destroyed.
     */
    public int getFindingsDestroyed() {
        return findingsDestroyed;
    }

    /**
     * Sets the number of findings destroyed by this Thesseus pawn.
     * <p>
     * Postcondition: The number of findings destroyed is updated.
     *
     * @param findingsDestroyed The number of findings destroyed by the Thesseus pawn.
     */
    public void setFindingsDestroyed(int findingsDestroyed) {
        this.findingsDestroyed = findingsDestroyed;
    }

    /**
     * Retrieves whether the Thesseus pawn has been attacked by a {@link Minotaur} card of the enemy player.
     * <p>
     * Postcondition: Returns {@code true} if the pawn has been attacked, otherwise returns {@code false}.
     *
     * @return {@code true} if the Thesseus pawn has been attacked, otherwise {@code false}.
     */
    public boolean hasBeenAttacked() {
        return hasBeenAttacked;
    }

    /**
     * Sets the attacked state of the Thesseus pawn.
     * <p>
     * Postcondition: The attacked state is updated to reflect whether the pawn has been attacked by a {@link Minotaur} card of the enemy player.
     *
     * @param hasBeenAttacked {@code true} if the Thesseus pawn has been attacked, otherwise {@code false}.
     */
    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }
}

