package src.View;

import jdk.nashorn.internal.scripts.JO;
import src.model.Player;
import src.model.findings.Finding;
import src.model.positions.FindingPosition;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class HistoricalFindInfo extends JOptionPane {
    public HistoricalFindInfo(Player plr, FindingPosition pos) throws IOException {
        File csvInfo = null;
        setSize(400, 400);
        try {
            csvInfo = new File("src/csvFiles/csv_greek.csv");
        } catch (Exception e) {
            throw new FileNotFoundException("CSV file not found" + csvInfo.getPath());
        }
        Icon image = null;
        String title = null;
        String message = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvInfo))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by semicolon
                String[] info = line.split(";", 3);  // Splitting into 3 parts

                if (info.length == 3) {
                    // Assign each part to a variable
                    ImageIcon orIm = new ImageIcon("src/assets/images/" + info[0].trim());
                    Image scaledIm = orIm.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    image = new ImageIcon(scaledIm);
                    title = info[1].trim();
                    message = info[2].trim();
                    // Process the data
                    String filePath = "src/assets/images/" + info[0].trim();
                    int last1 = filePath.lastIndexOf('/');
                    int last2 = filePath.lastIndexOf('.');
                    if (last1 != -1 && last2 != -1 && last2 > last1) {
                        String findName = filePath.substring(last1 + 1, last2);
                        if (findName.equals(pos.getFind().getFindingName())) {
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + csvInfo.getPath());
        }
        String wrappedMessage = "<html><body style='width: 300px'>" + message + "</body></html>";
        showMessageDialog(null, wrappedMessage, title, JOptionPane.INFORMATION_MESSAGE, image);
    }
}