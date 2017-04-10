package s17cs350task1;

import javafx.geometry.Point2D;
import javafx.geometry.Dimension2D;
import java.util.*;
import java.util.List;

/**
 * Created by: bimpecoven
 * Created on: 4/4/17.
 */
 public class Box implements Cloneable {

    private String id;
    private Dimension2D size;
    private boolean isRoot;
    private List<Connector> childConnectors;
    private Connector parentConnector;

    public Box(String id, Dimension2D size) {
        this.parentConnector = null;
        this.id = id;
        this.size = size;
        this.isRoot = false;
        this.childConnectors = new ArrayList<Connector>();
    }//end constructor - NON ROOT

    public Box(String id, Dimension2D size, boolean isRoot) {
        this.parentConnector = null;
        this.id = id;
        this.size = size;
        this.isRoot = isRoot;
        this.childConnectors = new ArrayList<Connector>();
    }//end constructor - ROOT

    public Box clone() throws CloneNotSupportedException {
        Box cloned = (Box)super.clone();

        cloned.id = new String(this.id);
        cloned.size = new Dimension2D(this.size.getWidth(), this.size.getHeight());
        cloned.isRoot = this.isRoot;
        cloned.childConnectors = new ArrayList<Connector>();

        for (int x = 0; x < this.childConnectors.size(); x++) {
            Connector temp = this.childConnectors.get(x).clone();
            cloned.connectChild(temp);
        }//end for

        return cloned;
    }//end clone

    public void connectChild(Connector connector) {
        this.childConnectors.add(connector);
        connector.setParentBox(this);
    }//end connectChild

    public Point2D getAbsoluteCenterPosition() {
        if (this.isRoot) {
            return new Point2D(0, 0);
        }//end if
        else {
            Point2D currentOffset = this.getConnectorToParent().getOffsetFromParentBox();
            Point2D parentsOffset = this.parentConnector.getParentBox().getAbsoluteCenterPosition();
            Point2D totalOffset = currentOffset.add(parentsOffset);
            return totalOffset;
        }//end else
    }//end getAbsoluteCenterPosition

    public int getChildBoxCount() {
        return getChildBoxes().size();
    }//end getChildBoxCount

    public List<Box> getChildBoxes() {
        List<Box> childBoxes = new ArrayList<Box>();
        for (Connector c : this.childConnectors ) {
            childBoxes.add(c.getChildBox());
        }//end for

        Collections.sort(childBoxes, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Integer.parseInt(o1.getID()) - Integer.parseInt(o2.getID());
            }
        });
        return childBoxes;
    }//end getChildBoxes

    public List<Connector> getConnectorsToChildren() {
        return this.childConnectors;
    }//end getConnectorsToChildren

    public Connector getConnectorToParent() {
        return this.parentConnector;
    }//end getConnectorToParent

    public int getDescendantBoxCount() {
        return getDescendantBoxes().size();
    }//end getDescendantBoxCount

    public List<Box> getDescendantBoxes() {
        List<Box> descendantBoxes = new ArrayList<Box>();
        for (Connector c : childConnectors) {
            descendantBoxes.add(c.getChildBox());
            if (c.getChildBox().getChildBoxCount() > 0) {
                descendantBoxes.addAll(c.getChildBox().getDescendantBoxes());
            }//end if
        }//end for
        Collections.sort(descendantBoxes, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Integer.parseInt(o1.getID()) - Integer.parseInt(o2.getID());
            }
        });
        return descendantBoxes;
    }//end getDescendantBoxes

    public String getID() {
        return this.id;
    }//end getID

    public Dimension2D getSize() {
        return this.size;
    }//end getSize

    public boolean hasConnectorToParent() {
        if (this.isRoot) {
            return false;
        }//end if
        else {
            if (parentConnector == null) {
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
        this.parentConnector = connector;
    }//end setConnectorToParent

    public String toString() {
        String str = getID();

        return str;
    }//end toString

}//end Class
