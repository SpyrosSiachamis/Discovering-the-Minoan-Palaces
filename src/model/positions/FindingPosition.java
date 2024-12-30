package src.model.positions;

import src.model.findings.Finding;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FindingPosition extends SimplePosition{
    private Finding find; // Finding object associated with the FindingPosition

    /**
     * Initializes a FindingPosition object with the given x-coordinate, Path object, points value, Square object, and Finding object.
     *
     * @param x The x-coordinate of the FindingPosition.
     * @param path The Path name associated with the FindingPosition.
     * @param square The Square object associated with the FindingPosition.
     */
    public FindingPosition(int x, String path, Square square) {
        super(x, path, square);
        square.SquareLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Finding Position right");
                }
                else{
                    if (find != null){
                        System.out.println(find.getFindingName().toString());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * Retrieves the Finding object associated with the FindingPosition instance.
     *
     * @return The Finding object associated with the FindingPosition.
     */
    public Finding getFind() {
        return find;
    }

    /**
     * Sets the Finding object associated with the FindingPosition instance.
     *
     * @param find The Finding object to be associated with the FindingPosition instance.
     */
    public void setFind(Finding find) {
        this.find = find;
    }


}
