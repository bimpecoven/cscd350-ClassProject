import s17cs350task1.Connector;
import s17cs350task1.Box;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by: bimpecoven
 * Created on: 4/6/17.
 */
public class BoxConnectorTester {
    public static void main(String[]args) throws CloneNotSupportedException {
        List<Box> boxes = new ArrayList<Box>();
        int pointer = 0, treeSize = 0;

        for(int x = 0; x < 20; x++) {
            if (x == 0) {
                boxes.add(new Box("Root", new Dimension2D(2, 2), true));
            }//end if
            else {
                boxes.add(new Box(Integer.toString(x) , new Dimension2D(2, 2)));
            }//end else
        }//end for

        for(int x = 0; x < boxes.size() - 1; x++) {
            // this will cause each box to have 'i' children, we can anticipate the results
            if (treeSize % 3 == 0 && treeSize != 0) {
                pointer++;
            }//end if

            boxes.get(pointer).connectChild(new Connector(boxes.get(x+1), new Point2D(5, 5)));
            treeSize++;
        }//end for

        System.out.println();
        System.out.println("Root Box descendant count: " + boxes.get(0).getDescendantBoxCount() + ", descendant boxes: ");
        System.out.println(boxes.get(0).getDescendantBoxes().toString());
        System.out.println();
        System.out.println("Testing specific children. - 5 - expecting: [16, 17, 18]");
        System.out.println("Result: " + boxes.get(5).getChildBoxes().toString());
        System.out.println();
        System.out.println("Adding new box: 99 - child of 1.");
        boxes.get(1).connectChild(new Connector(new Box("99", new Dimension2D(2, 2)), new Point2D(5, 5)));
        System.out.println("Adding new box: 24 - child of 1.");
        boxes.get(1).connectChild(new Connector(new Box("24", new Dimension2D(2, 2)), new Point2D(5, 5)));
        System.out.println();
        System.out.println("Testing specific specific children - 1 - expecting: [4, 5, 6, 24, 99]");
        System.out.println("Result: " + boxes.get(1).getChildBoxes().toString());
        System.out.println();
        System.out.println("Testing Clone.");
        Box cloned = boxes.get(0).clone();
        System.out.println("Root Box: " + boxes.get(0).getDescendantBoxes().toString());
        System.out.println("Cloned Box: " + cloned.getDescendantBoxes().toString());
        System.out.print(".equals() result: " + boxes.get(0).equals(cloned));
    }//end main
}//end class
