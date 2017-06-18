package models;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public interface Graph {

    /**
     * add a new node to the graph.
     * @param node the node to be added.
     */
    void addNode(Node node);

    /**
     * add a new edge to the graph.
     * @param edge the edge to be added.
     */
    void addEdge(Edge edge);

    /**
     * checks whether a node is in the graph or not.
     * @param node to check its presence in the graph
     * @return true if the graph contains the node else false
     */
    Boolean contains(Node node);

    /**
     * gets all the nodes in the graph.
     * @return a list of all the nodes in the graph
     */
    List<Node> getNodes();

    /**
     * return a list of edges that start from a specific node
     * @param node to get all edges starting from it.
     * @return a list of all edges starting from the passed node.
     */
    List<Edge> getEdgesFromNode(Node node);

    /**
     * get the index of a specific node in the graph.
     * @param node to get its index
     * @return the index of the required node or -1 if not found.
     */
    int getNodeIndex(Node node);

    /**
     * gets the number of nodes in the graph.
     * @return the number of nodes in the graph.
     */
    int getNodesCount();

    /**
     * get the number of edges in the graph.
     * @return the number of edges in the graph.
     */
    int getEdgesCount();

    /**
     * gets the node with the passed title.
     * @param title of the required node.
     * @return the node with this title or null if not found.
     */
    Node getNode(String title);
}
