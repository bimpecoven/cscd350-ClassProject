import s17cs350task1.*;

import javafx.geometry.Point3D;
import java.util.ArrayList;
import java.util.List;

   /* Author: Cameron Hilsmann
    * Created: April 10th, 2017
    * Performs multi-layered testing for Box.Java/Connector.Java
    */

public class Tester
{
    public static void main(String [] args)throws CloneNotSupportedException
    {
        List<Box> boxes = new ArrayList<Box>();
        Box a = new Box("a", new Dimension3D(3,5,2), true);
        boxes.add(a);
        Box a_b1 = new Box("a_b1", new Dimension3D(4,6,2));
        boxes.add(a_b1);
        Box a_b2 = new Box("a_b2", new Dimension3D(8,7,2));
        boxes.add(a_b2);
        Box a_b3 = new Box("a_b3", new Dimension3D(2,5,2));
        boxes.add(a_b3);
        Box a_b3_c1 = new Box("a_b3_c1", new Dimension3D(2,5,2));
        boxes.add(a_b3_c1);
        Box a_b3_c1_d1 = new Box("a_b3_c1_d1", new Dimension3D(2,5,2));
        boxes.add(a_b3_c1_d1);
        Box a_b2_c1 = new Box("a_b2_c1", new Dimension3D(2,5,2));
        boxes.add(a_b2_c1);
        Box a_b2_c2 = new Box("a_b2_c2", new Dimension3D(2,5,2));
        boxes.add(a_b2_c2);

        a.connectChild(new Connector(a_b3, new Point3D(10,12,3)));
        a.connectChild(new Connector(a_b1, new Point3D(-2,-15,4)));
        a.connectChild(new Connector(a_b2, new Point3D(8, -23,1)));
        a_b3.connectChild(new Connector(a_b3_c1, new Point3D(7,-8,8)));
        a_b3_c1.connectChild(new Connector(a_b3_c1_d1, new Point3D(5, 1,4)));
        a_b2.connectChild(new Connector(a_b2_c2, new Point3D(32, 10,3)));
        a_b2.connectChild(new Connector(a_b2_c1, new Point3D(5,9,6)));

        List<Box> BoxAChild = a.getChildBoxes();
        System.out.println("Box a children: [Should be a_b1, a_b2, and a_b3]: ");
        for(int x = 0; x < BoxAChild.size(); x++){
            System.out.println("Box ID: " + BoxAChild.get(x));
        }
        System.out.println("-------------------------");
        System.out.println("Box a_b2 children: [Should be a_b2_c1 and a_b2_c2]: ");
        List<Box> BoxAB2Child = a_b2.getChildBoxes();
        for(int x = 0; x < BoxAB2Child.size(); x++){
            System.out.println("Box ID: " + BoxAB2Child.get(x));
        }

        System.out.println("-------------------------");

        List<Box> BoxAB3Child = a_b3.getChildBoxes();
        System.out.println("Box a_b3 children: [Should be a_b3_c1]: ");
        for(int x = 0; x < BoxAB3Child.size(); x++){
            System.out.println("Box ID: " + BoxAB3Child.get(x));
        }

        System.out.println("-------------------------");
        System.out.println("Box a_b3_c1 children: [Should be a_b3_c1_d1]: ");
        List<Box> BoxAB3CChild = a_b3_c1.getChildBoxes();
        for(int x = 0; x < BoxAB3CChild.size(); x++){
            System.out.println("Box ID: " + BoxAB3CChild.get(x));
        }

        System.out.println("-------------------------");

        List<Box> ChildA = a.getDescendantBoxes();
        System.out.println("Box a descendants [Should be a_b1, a_b2, a_b2_c1, a_b2_c2, a_b3, a_b3_c1, a_b3_c1_d1] ");
        for(int x = 0; x < ChildA.size(); x++){
            System.out.println("Box ID: " + ChildA.get(x));
        }

        System.out.println("-------------------------");
        System.out.println("Box a_b2 descendants [Should be boxes a_b2_c1 and a_b2_c2] ");
        List<Box> ChildAB2 = a_b2.getDescendantBoxes();
        for(int x = 0; x < ChildAB2.size(); x++){
            System.out.println("Box ID: " + ChildAB2.get(x));
        }

        System.out.println("-------------------------");
        System.out.println("Box a_b3 descendants [Should be boxes a_b3_c1 and a_b3_c1_d1] ");
        List<Box> ChildAB3 = a_b3.getDescendantBoxes();
        for(int x = 0; x < ChildAB3.size(); x++){
            System.out.println("Box ID: " + ChildAB3.get(x));
        }

        System.out.println("-------------------------");

        System.out.println("Box a_b3_c1 descendants [Should be boxes a_b3_c1_d1] " );
        List<Box> ChildAB3C1 = a_b3_c1.getDescendantBoxes();
        for(int x = 0; x < ChildAB3C1.size(); x++){
            System.out.println("Box ID: " + ChildAB3C1.get(x));
        }

        System.out.println("-------------------------");
        System.out.println("Now to check for clone!");
        System.out.println();
        System.out.println("Testing Individual Boxes for same address. False = Deep Clone:");
        System.out.println();
        //Can't trust yet - Maybe
        Box temp;
        for(int x = 0; x < boxes.size(); x++) {
            temp = boxes.get(x).clone();
            System.out.println("\nBox: "+boxes.get(x).getID());
            System.out.println("\nCloned Box: " + temp.toString());
            System.out.print("\n.equals() result: " + boxes.get(x).equals(temp));
            System.out.println("\n-------------------------------------------------");
        }
        System.out.println();
        System.out.println("Testing Root Copy. Temp = Cloned");
        System.out.println();
        temp = a.clone();
        List<Box> ChildTemp = temp.getDescendantBoxes();
        System.out.println("Temp Descendants [Should be a_b1, a_b2, a_b2_c1, a_b2_c2, a_b3, a_b3_c1, a_b3_c1_d1] " );
        for(int x = 0; x < ChildTemp.size(); x++){
            System.out.println("ID " + ChildTemp.get(x));
        }

        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Testing to see ID names are the same but Connector to parent is different:");
        System.out.println();
        for(int x = 0; x < ChildTemp.size(); x++){
            System.out.println("name of Cloned(temp): " + ChildTemp.get(x).getID());
            System.out.println("name of Original: " + ChildA.get(x).getID());
            System.out.println("Parent Connector of cloned: " +ChildTemp.get(x).getConnectorToParent());
            System.out.println("Parent Connector of Original: " +ChildA.get(x).getConnectorToParent());
            System.out.println(".equals() result: "+ ChildA.get(x).equals(temp));
            System.out.println("-----------------------------------");
            System.out.println();
        }

        System.out.println("Test looped connection");
//        boxes.get(6).connectChild(new Connector(a, new Point3D(10,12,3)));
        System.out.println("Attempted parent: " + boxes.get(6) + " Attempted child: " + boxes.get(1));

        try {
            a_b1.connectChild(new Connector(a_b2, new Point3D(10, 12, 3)));
            a_b2.connectChild(new Connector(a_b1, new Point3D(10, 12, 3)));
        }//end try
        catch(TaskException e) {
            System.out.println("Caught looping boxes attempt");
        }//end catch

        try {
            boxes.get(6).connectChild(new Connector(boxes.get(1), new Point3D(10, 12, 3)));
        }//end try
        catch (TaskException e) {
            System.out.println("Caught another attempt to loop boxes");
        }//end catch
    }//end main
}//end class
