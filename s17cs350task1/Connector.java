package s17cs350task1;

import javafx.geometry.Point3D;

import java.util.List;

/**
 * Created by: Impecoven, Blake
 * Created on: 4/4/17
 */

/* Tree Validation checking
 *
 * Constructor pre conditions
 *
**/

public class Connector extends Object implements Cloneable {

    private Box childBox;
    private Point3D offsetFromParentBox;
    private Box parentBox;

    public Connector(Box childBox, Point3D offsetFromParentBox) {
        if (childBox == null) {
            throw new TaskException("Error - Child box is null.");
        }//end if
        if (offsetFromParentBox == null) {
            throw new TaskException("Error - Offset is null.");
        }//end if
        if (childBox.hasConnectorToParent()) {
            throw new TaskException("Error - Child box already has a parent.");
        }//end if

        this.childBox = childBox;
        this.childBox.setConnectorToParent(this);
        this.offsetFromParentBox = offsetFromParentBox;
        this.parentBox = null;
    }//end Constructor

    public Connector clone() throws CloneNotSupportedException {
        Connector cloned = (Connector)super.clone();
        cloned.offsetFromParentBox = new Point3D(offsetFromParentBox.getX(), offsetFromParentBox.getY(), offsetFromParentBox.getZ());
        cloned.childBox = (Box)this.childBox.clone();
        cloned.parentBox = null;
        cloned.childBox.setConnectorToParent(cloned);
        return cloned;
    }//end clone

    public Box getChildBox() {
        return this.childBox;
    }//end getChildBox

    public Point3D getOffsetFromParentBox() {
        return this.offsetFromParentBox;
    }//end getOffsetFromParentBox

    public Box getParentBox() {
        if (this.hasParentBox()) {
            return this.parentBox;
        }//end if
        else {
            throw new TaskException("Error - There is no parent box.");
        }//end else
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
        if (!validID(parentBox, this.childBox)) {
            throw new RuntimeException("Error - Box already exists with same ID.");
        }//end if

        this.parentBox = parentBox;
    }//end setParentBox

    private Box moveUpTree(Box box) {
        while (box.hasConnectorToParent()) {
            box = box.getConnectorToParent().getParentBox();
        }//end while

        return box;
    }//end moveUpTree

    private boolean validID(Box parentBox, Box childBox) {
        Box root;

        if (!parentBox.isRoot()) {
            root = moveUpTree(parentBox);
        }//end if
        else {
            root = parentBox;
        }//end else

        List<Box> parentDescendants = root.getDescendantBoxes();
        parentDescendants.add(root);
        List<Box> childDescendants = childBox.getDescendantBoxes();


        for (Box parent : parentDescendants) {
            for (Box child : childDescendants) {
                if (parent.getID().equals(child.getID())) {
                    return false;
                }//end if
            }//end for
        }//end for
        return true;
    }//end validID

}//end Class
