import javafx.geometry.Point2D;

/**
 * Created by: bimpecoven
 * Created on: 4/4/17.
 */
public class Connector extends Object implements Cloneable {

    private Box childBox;
    private Point2D offsetFromParentBox;
    private Box parentBox;

    public Connector(Box childBox, Point2D offsetFromParentBox) {
        this.childBox = childBox;
        this.offsetFromParentBox = offsetFromParentBox;
    }//end Constructor

    public Connector clone() {
        return new Connector(this.childBox, this.offsetFromParentBox);
    }//end clone

    public Box getChildBox() {
        return this.childBox;
    }//end getChildBox

    public Point2D getOffsetFromParentBox() {
        return this.offsetFromParentBox;
    }//end getOffsetFromParentBox

    public Box getParentBox() {
        return this.parentBox;
    }//end getParentBox

    public boolean hasParentBox() {
        if (this.parentBox == null) {
            return false;
        }//end if
        else {
            return true;
        }//end else
    }//end hasParentBox

    public void setParentBox(Box parentBox) {
        this.parentBox = parentBox;
    }//end setParentBox

    public String toString() {
        String str = "";

        return str;
    }//end toString

}//end Class
