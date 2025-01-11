package src.model.pawns;

import src.model.Player;
import src.model.positions.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Arch extends Pawn{
    ImageIcon image;
    int xPos;
    JLabel pawn;
    /**
     * Constructs a new Pawn object with the given Position and Image.
     *
     */
    public Arch(ImageIcon image, Player player) {
        Image scaledImage = image.getImage().getScaledInstance(30,50,Image.SCALE_SMOOTH);
        pawn = new JLabel(new ImageIcon(scaledImage));
        if (player.getName().equals("1")) {
            pawn.setBorder(new LineBorder(Color.RED));
        }
        else if (player.getName().equals("2")) {
            pawn.setBorder(new LineBorder(Color.GREEN));
        }
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
