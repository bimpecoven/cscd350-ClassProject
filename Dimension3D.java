package s17cs350task1;

import javafx.geometry.Dimension2D;

/**
 * Created by: Impecoven, Blake
 * Created on: 4/14/17
 */
public class Dimension3D implements Cloneable {
    private double width; //x
    private double height; //y
    private double depth; //z

    public Dimension3D(double width, double height, double depth) {
        if (width*100 <= 0 || height*100 <= 0 || depth*100 <= 0) {
            throw new TaskException("Dimension's cannot be less than 0.");
        }//end if

        this.width = width;
        this.height = height;
        this.depth = depth;
    }//end DVC

    public double getWidth() { return this.width; }

    public double getHeight() { return this.height; }

    public double getDepth() { return this.depth; }
}//end class
