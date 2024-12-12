package src.View;

import javax.swing.*;
import java.awt.*;

public class wallPaintingsWin extends JFrame {
    public wallPaintingsWin() {
        setSize(500,500);
        ImageIcon icon = new ImageIcon("src/assets/images/findings/fresco6_15.jpg");
        setIconImage(icon.getImage());

        setTitle("Τοιχογραφίες Παίκτη 1");
        setResizable(true);
        setAlwaysOnTop(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
