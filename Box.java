import javafx.geometry.Point2D;

import java.awt.geom.Dimension2D;
import java.util.*;
import java.util.List;

/**
 * Created by: bimpecoven
 * Created on: 4/4/17.
 */
public class Box {

    private String id;
    private Dimension2D size;
    private boolean isRoot;
    private List<Connector> childConnectors;
    private Connector parentConnector;

    public Box(String id, Dimension2D size) {
        this.id = id;
        this.size = size;
        this.isRoot = false;
        childConnectors = new ArrayList<Connector>();
    }//end constructor - NON ROOT

    public Box(String id, Dimension2D size, boolean isRoot) {
        this.id = id;
        this.size = size;
        this.isRoot = isRoot;
        childConnectors = new ArrayList<Connector>();
    }//end constructor - ROOT

    public Box clone() {
        return new Box(this.id, this.size, this.isRoot);
    }//end clone

    public void connectChild(Connector connector) {
        this.childConnectors.add(connector);
    }//end connectChild

    public Point2D getAbsoluteCenterPosition() {

    }//end getAbsoluteCenterPosition

    public int getChildBoxCount() {
        int count = 0;

        return count;
    }//end getChildBoxCount

    public List<Box> getChildBoxes() {

    }//end getChildBoxes

    public List<Connector> getConnectorsToChildren() {
        return this.childConnectors;
    }//end getConnectorsToChildren

    public Connector getConnectorToParent() {
        return this.parentConnector;
    }//end getConnectorToParent

    public int getDescendantBoxCount() {
        int count = 0;

        return count;
    }//end getDescendantBoxCount

    public List<Box> getDescendantBoxes() {

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
        String str = "";

        return str;
    }//end toString

}//end Class
