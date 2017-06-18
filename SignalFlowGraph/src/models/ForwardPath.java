package models;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public interface ForwardPath {

    /**
     * add a new node to the forward path.
     * @param node to be added to the forward path.
     */
    void addNode(Node node);

    /**
     * add edge gain to the total gain of the forward path.
     * @param gain to be added to the total gain of the forward path.
     */
    void addEdgeGain(double gain);

    /**
     * remove the last node from the forward path.
     * and remove its passed gain from total gain of the forward path.
     * @param lastGain to be removed from total gain of the forward path.
     */
    void removeLastNodeAndGain(double lastGain);

    /**
     * get a new identical forward path.
     * @return a new identical forward path.
     */
    ForwardPath clone();

    /**
     * gets the total gain of the forward path.
     * @return the total gain of the forward path.
     */
    double getTotalGain();

    /**
     * sets the total gain of the forward path.
     * @param totalGain to be set to the forward path.
     */
    void setTotalGain(double totalGain);

    /**
     * gets all the nodes in the forward path.
     * @return a list of all the nodes in the forward path.
     */
    List<Node> getNodes();

    /**
     * sets all the nodes in the forward path.
     * @param nodes to be set to the forward path.
     */
    void setNodes(List<Node> nodes);

    /**
     * checks if the forward path intersects with a loop or not.
     * @param loop to check its intersection.
     * @return true if loop intersects the forward path else false.
     */
    boolean intersects(Loop loop);
}
