package src.View;

import src.model.Player;
import src.model.findings.Finding;
import src.model.findings.Fresco;

import javax.swing.*;
import java.awt.*;

public class wallPaintingsWin extends JFrame {
    public wallPaintingsWin(Player plr) {
        int rows = 1;
        int cols = 0;
        for (int i=0; i < plr.getFinds().size(); i++) {
            if (plr.getFinds().get(i) instanceof Fresco) {
                cols++;
                if (cols % 3 == 0) {
                    rows++;
                }
            }
        }
        GridLayout gridLayout = new GridLayout(rows,cols);

        setSize(500,500);
        ImageIcon icon = new ImageIcon("src/assets/images/findings/fresco6_15.jpg");
        setIconImage(icon.getImage());
        JPanel info = new JPanel();
        JLabel playerInfo = new JLabel("Player "+ plr.getName() + " Frescos");
        JPanel findings = new JPanel(gridLayout);
        for (Finding fresco : plr.getFinds()) {
            if (fresco instanceof Fresco) {
                findings.add(((Fresco) fresco).getFrescoImage());
            }
        }
        setTitle("Τοιχογραφίες Παίκτη " + plr.getName());
        add(info);
        info.add(playerInfo);
        add(findings);
        setResizable(true);
        setAlwaysOnTop(true);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
