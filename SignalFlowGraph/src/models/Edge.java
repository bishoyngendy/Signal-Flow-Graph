package models;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public interface Edge {

    /**
     * gets the gain of the edge.
     * @return the gain of the edge.
     */
    double getGain();

    /**
     * sets the gain of the edge.
     * @param gain to set to the edge.
     */
    void setGain(double gain);

    /**
     * gets the source node of the edge.
     * @return the source node of the edge.
     */
    Node getSource();

    /**
     * sets the node node of the edge.
     * @param source node to be set to the edge.
     */
    void setSource(Node source);

    /**
     * gets the destination node of the edge.
     * @return the destination node of the edge.
     */
    Node getDestination();

    /**
     * sets the destination node of the edge.
     * @param destination node to be set to the edge.
     */
    void setDestination(Node destination);

}
