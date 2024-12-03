package Phase1.model.positions;

import javax.swing.*;
import Phase1.model.findings.Finding;
public class FindingPosition extends SimplePosition implements Position{
    private Finding find;

    /**
     *
     * @param image Image of position.
     * @param points Points of position.
     */
    public FindingPosition(ImageIcon image, int points) {
        super(image, points);
    }

    /**
     *
     * @return Archaeological Finding
     */
    public Finding getFind() {
        return find;
    }

    /**
     *
     * @param find Takes an Archaeological Finding object
     */
    public void setFind(Finding find) {
        this.find = find;
    }
}
