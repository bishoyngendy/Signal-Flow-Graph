package models;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on Tuesday 18/04/2017.
 */
public interface Loop {

    /**
     * checks whether a node is intersecting this loop or not.
     * @param node the node to check for.
     * @return true if the node intersects the loop else false.
     */
    Boolean intersects(Node node);

    /**
     * checks whether a loop is intersecting this loop or not.
     * @param loop the loop to check for.
     * @return true if the loop intersects the loop else false.
     */
    Boolean intersects(Loop loop);

    /**
     * gets the gain of the loop.
     * @return the gain of the loop.
     */
    int getGain();

    /**
     * sets the gain of the loop.
     * @param gain to set to the loop.
     */
    void setGain(int gain);

    /**
     * add new node to the loop.
     * @param node to be added the the loop.
     */
    void addNode(Node node);

    /**
     * gets all the nodes in the loop.
     * @return all the nodes in the loop.
     */
    List<Node> getNodes();
}
