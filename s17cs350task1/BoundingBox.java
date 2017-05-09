package s17cs350task1;

import javafx.geometry.Point3D;

import java.util.List;

/**
 * Created by: Impecoven, Blake
 * Created on: 4/4/17
 */

public class BoundingBox implements Cloneable {
    private Point3D center;
    private Dimension3D size;

    public BoundingBox(Point3D center, Dimension3D size) {
        this.center = center;
        this.size = size;
    }//end EVC

    public static enum E_Plane {
        XY("The xy plane"), XZ("The xz plane"), YZ("The yz plane");
        private final String plane;

        E_Plane(String plane) {
            this.plane = plane;
        }//end constructor
    }//end enum

    public double calculateArea(BoundingBox.E_Plane plane) {

    }//end calculateArea

    public double calculateVolume() {
        if (this.size == null) {
            throw new TaskException("Error - Size is null");
        }//end if
        else {
            return this.size.getHeight() * this.size.getWidth() * this.size.getDepth();
        }//end else
    }//end calculateDouble

    public BoundingBox clone() {
        Point3D newCenter = new Point3D(this.center.getX(), this.center.getY(), this.center.getZ());
        Dimension3D newSize = new Dimension3D(this.size.getWidth(), this.size.getHeight(), this.size.getDepth());
        return new BoundingBox(newCenter, newSize);
    }//end clone

    public BoundingBox extend(BoundingBox boundingBox) {

    }//end extend

    public List<Point3D> generateCorners(){

    }//end generateCorners

    public Point3D getCenter() {
        if (this.center == null) {
            throw new TaskException("Error - Center is null.");
        }//end if
        else {
            return this.center;
        }//end else
    }//end getCenter

    public Dimension3D getSize() {
        if (this.size == null) {
            throw new TaskException("Error - Size is null.");
        }//end if
        else {
            return this.size;
        }//end else
    }//end getSize

    public String toString() {
        String str = "";

        return str;
    }//end toString
}//end class
