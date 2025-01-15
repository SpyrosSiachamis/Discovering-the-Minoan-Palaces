package src.View;

import src.model.Player;
import src.model.findings.Finding;
import src.model.findings.Fresco;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code WallPaintingsWin} class represents a window displaying the player's collected frescoes (wall paintings).
 * This window shows the images of all frescoes the player has found, as well as the player's name in the title.
 * <p>
 * <b>Pre-condition</b>: A valid {@link Player} object and an ArrayList of {@link Finding} objects must be provided to the constructor.
 * <p>
 * <b>Post-condition</b>: The window displays the frescoes as images within the panel, adding each fresco's image to the layout.
 * <p>
 * <b>Invariant</b>: The window contains only frescoes related to the given player, and the images are displayed using {@link JLabel}s.
 */
public class WallPaintingsWin extends JFrame {
    private JPanel paintings;
    /**
     * Constructs a new {@code WallPaintingsWin} window for displaying the frescoes (wall paintings) collected by the player.
     * Initializes the window size, layout, and adds fresco images if the player has any.
     *
     * @param findings The list of {@link Finding} objects that represent the player's collected findings.
     * @param plr The player whose frescoes are displayed in this window.
     */
    public WallPaintingsWin(ArrayList<Finding> findings, Player plr) {
        setTitle("Τοιχογραφίες Παίκτη " + plr.getName());
        setResizable(true);
        setAlwaysOnTop(true);
        setFocusable(false);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        paintings = new JPanel();
        paintings.setLayout(new BoxLayout(paintings, BoxLayout.PAGE_AXIS));
        for (Finding f : findings) {
            JLabel frescoLabel;
            if (f instanceof Fresco) {
                frescoLabel = ((Fresco) f).getFrescoImage();
                paintings.add(frescoLabel);
            }
        }
        add(paintings);
    }
}
