package src.View;
import src.model.Player;
import src.model.positions.FindingPosition;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * The {@code HistoricalFindInfo} class provides a dialog window displaying historical information
 * about a specific finding when a player excavates it. The information, including an image, title,
 * and description, is fetched from a CSV file based on the name of the finding.
 * <p>
 * <b>Pre-condition</b>: A player and a finding position must be provided to the constructor.
 * <p>
 * <b>Post-condition</b>: A dialog is displayed showing the image, title, and description associated
 * with the finding the player interacts with.
 * <p>
 * <b>Invariant</b>: The CSV file containing information is read to find a matching entry for the given
 * finding. The player’s action leads to the appropriate historical data being displayed.
 */
public class HistoricalFindInfo extends JOptionPane {
    /**
     * Constructs a dialog to display historical information about a finding.
     * The information is loaded from a CSV file where each entry consists of an image filename, title, and description.
     * If the finding matches the entry in the CSV file, a dialog is shown with the finding's information.
     * <p>
     * <b>Post-condition</b>: Displays a message dialog containing the image, title, and description of the finding.
     *
     * @param plr The player interacting with the finding (not used directly in this constructor).
     * @param pos The position of the finding, which is used to match the relevant entry from the CSV file.
     * @throws IOException If there is an issue reading the CSV file or loading the image.
     */
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
                String[] info = line.split(";", 3);
                if (info.length == 3) {
                    ImageIcon orIm = new ImageIcon("src/assets/images/" + info[0].trim());
                    Image scaledIm = orIm.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    image = new ImageIcon(scaledIm);
                    title = info[1].trim();
                    message = info[2].trim();
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