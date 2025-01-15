package src.model.pawns;

import src.model.Player;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The {@code Arch} class represents a pawn in the game, specifically an "Arch" pawn. The pawn is represented by a
 * {@link JLabel} containing an image, and has a border color indicating the player controlling the pawn.
 * <p>
 * <b>Invariants:</b>
 * <ul>
 *   <li>The pawn has a border color set based on the player it belongs to (red for Player 1, green for Player 2).</li>
 *   <li>The image of the pawn is scaled to 30x50 pixels.</li>
 * </ul>
 */
public class Arch extends Pawn{
    /**
     * Constructs a new {@code Arch} object with the specified image and associated player.
     * <p>
     * The image for the arch is scaled to 30x50 pixels, and a border color is applied based on the player's name.
     * If the player’s name is "1", a red border is applied, while if the player’s name is "2", a green border is applied.
     * <p>
     * Precondition: The {@link Player} provided must have a non-null name.
     * <p>
     * Postcondition: The pawn is initialized with the scaled image and the appropriate border color based on the player.
     *
     * @param image The {@link ImageIcon} representing the image of the Arch pawn.
     * @param player The {@link Player} object that controls this pawn.
     */
    public Arch(ImageIcon image, Player player) {
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
     * Retrieves the {@link JLabel} representing the Arch pawn.
     * <p>
     * Postcondition: Returns the {@link JLabel} associated with this Arch pawn.
     *
     * @return The {@link JLabel} representing the Arch pawn.
     */
    @Override
    public JLabel getPawn() {
        return pawn;
    }
}
