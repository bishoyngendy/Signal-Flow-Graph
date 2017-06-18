package engine;

import models.Graph;
import models.Node;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 */
public interface Evaluator {
    /**.
     * Compute all needed to get the overall transfer function
     * @param graph the graph to compute on.
     * @param source the source of the graph.
     * @param destination the destination of the graph.
     * @return a paragraph string containing the steps and the result.
     */
    String getResultWithSteps(Graph graph, Node source, Node destination);
}
