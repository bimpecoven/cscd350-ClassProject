package s17cs350task1;

import javafx.concurrent.Task;
import javafx.geometry.Point3D;
import java.util.*;
import java.util.List;

/**
 * Created by: Impecoven, Blake
 * Created on: 4/4/17
 */

/* Tree Validation checking
 *
 * Constructor pre conditions
 * connectChild(Connector): null parameter check
 * Null checks on all passed in params
**/

 public class Box implements Cloneable {

    private String id;
    private Dimension3D size;
    private boolean isRoot;
    private List<Connector> childConnectors;
    private Connector parentConnector;

    public Box(String id, Dimension3D size) {
        if (id == null || id.isEmpty()) {
            throw new TaskException("Error - ID is empty/null.");
        }//end if
        if (size == null) {
            throw new TaskException("Error - Size is null.");
        }//end if

        this.parentConnector = null;
        this.id = id;
        this.size = size;
        this.isRoot = false;
        this.childConnectors = new ArrayList<Connector>();
    }//end constructor - NON ROOT

    public Box(String id, Dimension3D size, boolean isRoot) {
        if (id == null || id.isEmpty()) {
            throw new TaskException("Error - ID is empty.");
        }//end if
        if (size == null) {
            throw new TaskException("Error - Size is null.");
        }//end if

        this.parentConnector = null;
        this.id = id;
        this.size = size;
        this.isRoot = isRoot;
        this.childConnectors = new ArrayList<Connector>();
    }//end constructor - ROOT

    public Box clone() throws CloneNotSupportedException {
        Box cloned = (Box)super.clone();

        cloned.parentConnector = null;
        cloned.id = new String(this.id);
        cloned.size = size.clone();
        cloned.isRoot = this.isRoot;
        cloned.childConnectors = new ArrayList<Connector>();

        for (int x = 0; x < this.childConnectors.size(); x++) {
            Connector temp = this.childConnectors.get(x).clone();
            cloned.connectChild(temp);
        }//end for

        return cloned;
    }//end clone

    public void connectChild(Connector connector) {
        if (connector == null) {
            throw new TaskException("Error - Passed in Connector is null within connectChild.");
        }//end if
        if (connector.getChildBox().isRoot()) {
            throw new TaskException("Error - Child box is root.");
        }//end if
        if (!validID(this, connector.getChildBox())) {
            throw new TaskException("Error - Box already exists with the same ID.");
        }//end if

        connector.setParentBox(this);
        this.childConnectors.add(connector);
    }//end connectChild

    public Point3D getAbsoluteCenterPosition() {
        if (this.isRoot) {
            return new Point3D(0, 0, 0);
        }//end if
        else if (!this.hasConnectorToParent()) {
            throw new TaskException("Error - Has no root.");
        }//end if
        else {
            Point3D currentOffset = this.getConnectorToParent().getOffsetFromParentBox();
            Point3D parentsOffset = this.parentConnector.getParentBox().getAbsoluteCenterPosition();
            Point3D totalOffset = currentOffset.add(parentsOffset);
            return totalOffset;
        }//end else
    }//end getAbsoluteCenterPosition

    public int getChildBoxCount() {
        if (this.childConnectors == null) {
            throw new TaskException("Error - Child boxes not initialized.");
        }//end if
        else {
            return getChildBoxes().size();
        }//end
    }//end getChildBoxCount

    public List<Box> getChildBoxes() {
        if (this.childConnectors == null) {
            throw new TaskException("Error - Child boxes not initialized.");
        }//end if
        else {
            List<Box> childBoxes = new ArrayList<Box>();
            for (Connector c : this.childConnectors) {
                childBoxes.add(c.getChildBox());
            }//end for

            Collections.sort(childBoxes, new Comparator<Box>() {
                @Override
                public int compare(Box o1, Box o2) {
                    return o1.getID().compareTo(o2.getID());
                }
            });
            return childBoxes;
        }//end else
    }//end getChildBoxes

    public List<Connector> getConnectorsToChildren() {
        if (this.childConnectors == null) {
            throw new TaskException("Error - Child box connectors not initialized.");
        }//end if
        else {
            return new ArrayList<Connector>(this.childConnectors);
        }//end else
    }//end getConnectorsToChildren

    public Connector getConnectorToParent() {
        if (!this.hasConnectorToParent()) {
            throw new TaskException("Error - Box has no parent connector.");
        }//end if
        else {
            return this.parentConnector;
        }//end else
    }//end getConnectorToParent

    public int getDescendantBoxCount() {
        if (this.childConnectors == null) {
            throw new TaskException("Error - Child boxes not initialized.");
        }//end if
        else {
            return getDescendantBoxes().size();
        }//end else
    }//end getDescendantBoxCount

    public List<Box> getDescendantBoxes() {
        if(this.childConnectors == null) {
            throw new TaskException("Error - Child boxes not initialized.");
        }//end if
        else {
            List<Box> descendantBoxes = new ArrayList<Box>();
            for (Connector c : this.childConnectors) {
                descendantBoxes.add(c.getChildBox());
                if (c.getChildBox().getChildBoxCount() > 0) {
                    descendantBoxes.addAll(c.getChildBox().getDescendantBoxes());
                }//end if
            }//end for
            Collections.sort(descendantBoxes, new Comparator<Box>() {
                @Override
                public int compare(Box o1, Box o2) {
                    return o1.getID().compareTo(o2.getID());
                }
            });
            return descendantBoxes;
        }//end else
    }//end getDescendantBoxes

    public String getID() {
        if (this.id == null) {
            throw new TaskException("Error - ID is null.");
        }//end if
        else {
            return this.id;
        }//end else
    }//end getID

    public Dimension3D getSize() {
        if (this.size == null) {
            throw new TaskException("Error - Size is null.");
        }//end if
        else {
            return this.size;
        }//end else
    }//end getSize

    public boolean hasConnectorToParent() {
        if (this.isRoot) {
            return false;
        }//end if
        else {
            if (this.parentConnector == null) {
                return false;
            }//end if
            else {
                return true;
            }//end else
        }//end else
    }//end hasConnectorToParent

    public boolean isRoot() {
        return this.isRoot;
    }//end isRoot

    public void setConnectorToParent(Connector connector) {
        if (connector == null) {
            throw new TaskException("Error - Connector is null.");
        }//end if
        if (this.hasConnectorToParent()) {
            throw new TaskException("Error - Parent already set.");
        }//end

        this.parentConnector = connector;
    }//end setConnectorToParent

    public String toString() {
        String str = getID();

        return str;
    }//end toString

    private Box moveUpTree(Box box) {
        while (box.hasConnectorToParent() && box.getConnectorToParent().hasParentBox()) {
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
        childDescendants.add(childBox);


        for (Box parent : parentDescendants) {
            for (Box child : childDescendants) {
                if (parent.getID().equalsIgnoreCase(child.getID())) {
                    return false;
                }//end if
            }//end for
        }//end for

        return true;
    }//end validID

}//end Class
