package engine;

import models.ForwardPath;
import models.Graph;
import models.Node;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public interface ForwardPathsGetter {

    /**
     * gets all the forwardPaths between a specific source node and a specific destination node
     * @param graph the graph to get paths from
     * @param source the required source node
     * @param destination the required destination node
     * @return a list containing the required forward paths.
     */
    List<ForwardPath> getForwardPaths(Graph graph, Node source, Node destination);

}
