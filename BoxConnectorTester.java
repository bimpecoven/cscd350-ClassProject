import s17cs350task1.Connector;
import s17cs350task1.Box;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


/**
 * Created by: bimpecoven
 * Created on: 4/6/17.
 */
public class BoxConnectorTester {
    public static void main(String[]args) throws CloneNotSupportedException {
        Box rootBox = new Box("Root", new Dimension2D(2, 2), true);
        Box boxOne = new Box("One", new Dimension2D(2, 2));
        Box boxTwo = new Box("Two", new Dimension2D(2, 2));
        Box boxThree = new Box("Three", new Dimension2D(2, 2));

        rootBox.connectChild(new Connector(boxOne, new Point2D(5, 6)));
        rootBox.connectChild(new Connector(boxTwo, new Point2D(2, 4)));
        boxOne.connectChild(new Connector(boxThree, new Point2D(7, -3)));

        System.out.println("Testing Center Positions.");
        System.out.println(rootBox.getAbsoluteCenterPosition());
        System.out.println(boxOne.getAbsoluteCenterPosition());
        System.out.println(boxTwo.getAbsoluteCenterPosition());
        System.out.println(boxThree.getAbsoluteCenterPosition());

        System.out.println();
        System.out.println("Root Box child count: " + rootBox.getChildBoxCount() + ", child boxes: ");
        System.out.println(rootBox.getChildBoxes().toString());

        System.out.println();
        System.out.println("Root Box descendant count: " + rootBox.getDescendantBoxCount() + ", descendant boxes: ");
        System.out.println(rootBox.getDescendantBoxes().toString());

        System.out.println();
        System.out.println("Testing Clone.");
        Box cloned = rootBox.clone();
        System.out.println("Root Box: " + rootBox.getDescendantBoxes().toString());
        System.out.println("Cloned Box: " + cloned.getDescendantBoxes().toString());
        System.out.print(".equals() result: " + rootBox.equals(cloned));
    }//end main
}//end class
