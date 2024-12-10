package src.model.pawns;

import src.model.positions.Position;

import javax.swing.*;
import java.awt.*;

public class Arch extends Pawn{
    ImageIcon image;
    int xPos;
    /**
     * Constructs a new Pawn object with the given Position and Image.
     *
     */
    public Arch() {
        image = new ImageIcon("src/assets/images/pionia/arch.jpg");
    }

}
