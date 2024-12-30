package src.model.pawns;

import src.model.positions.Position;

import javax.swing.*;
import java.awt.*;

public class Thesseus extends Pawn {
    ImageIcon image;
    int xPos;
    JLabel pawn;

    /**
     * Constructs a new Pawn object with the given Position and Image.
     */
    public Thesseus(ImageIcon image) {
        Image scaledImage = image.getImage().getScaledInstance(50, 70, Image.SCALE_SMOOTH);
        pawn = new JLabel(new ImageIcon(scaledImage));
        pawn.setOpaque(false);
    }

    @Override
    public JLabel getPawn() {
        return pawn;
    }

    @Override
    public void setPawn(JLabel pawn) {
        this.pawn = pawn;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
}

