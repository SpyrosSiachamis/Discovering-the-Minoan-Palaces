package src.model.positions;

import src.View.HistoricalFindInfo;
import src.model.Player;
import src.model.findings.Finding;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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

    @Override
    public String toString() {
        return find.getFindingName();
    }
}
