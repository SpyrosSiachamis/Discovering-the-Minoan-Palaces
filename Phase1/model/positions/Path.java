package Phase1.model.positions;

import javax.swing.*;
import java.util.ArrayList;

public class Path{
    private ArrayList<Position> path = new ArrayList<Position>();
    private String pathName;
    private int positionPoint;

    /**
     * <b>Constructor</b>: Initializes a new Path Object.
     * @param pathName The name of the path, corresponding to the palace it belongs to.
     */
    public Path(String pathName) {
        this.pathName = pathName;
        int points = -20;
        for (int i=0; i<9; i++){
            if (points==-5){
                points = 5;
            }
            if(points ==20){
                points = 30;
            }
            if ((i+1) % 2 == 0){
                path.add(new SimplePosition(new ImageIcon("assets/images/paths/"+pathName+".jpg"),points));
            }
            if ((i+1)%2 == 1){
                FindingPosition find = new FindingPosition(new ImageIcon("assets/images/paths/"+pathName+"2.jpg"), points);
                path.add(find);
            }
            path.get(i).setPoints(points);
            points+=5;
            if (i==8){
                points = 50;
                FindingPosition palace = new FindingPosition(new ImageIcon("assets/images/paths/"+pathName+"Palace.jpg"),points);
                path.add(palace);
            }
        }
        path.remove(8);

    }

    /**
     * Returns the Position objects of the Path.
     * @return Positions of Path.
     */
    public ArrayList<Position> getPositions() {
        return path;
    }
}
