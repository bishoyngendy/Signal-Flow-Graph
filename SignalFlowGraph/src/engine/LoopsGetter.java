package engine;

import models.Graph;
import models.Loop;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public interface LoopsGetter {

    /**
     * get all the loops in a graph
     * @param graph the graph to get all nodes inside
     * @return a list containing all the loops in the graph
     */
    List<Loop> getLoops(Graph graph);

}
