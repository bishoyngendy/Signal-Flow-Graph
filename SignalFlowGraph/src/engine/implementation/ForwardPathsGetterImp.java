package engine.implementation;

import engine.ForwardPathsGetter;
import models.*;
import models.implementation.ForwardPathImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public class ForwardPathsGetterImp implements ForwardPathsGetter {

    /**
     * forward paths to be returned.
     */
    private List<ForwardPath> forwardPaths;

    public ForwardPathsGetterImp() {
        this.forwardPaths = new ArrayList<ForwardPath>();
    }

    public List<ForwardPath> getForwardPaths(Graph graph, Node source, Node destination) {
        if(!graph.contains(source) || !graph.contains(destination)) {
            throw new RuntimeException("Nodes must be in the graph");
        }

        // initialize DFS
        Boolean[] visited = new Boolean[graph.getNodesCount()];
        for(int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        // call to the dfs.
        dfs(graph, visited, source, destination, new ForwardPathImp(), 1);

        return forwardPaths;
    }

    /**
     * DFS Algorithm to get all forward paths in a graph.
     * between specific source and destination nodes.
     * @param graph reference to the graph object.
     * @param visited the visited array.
     * @param source the current source node.
     * @param destination the destination node.
     * @param path the current path till now.
     * @param lastGain gain of the last step.
     */
    private void dfs (Graph graph, Boolean[] visited, Node source, Node destination,
                     ForwardPath path, double lastGain) {

        visited[graph.getNodeIndex(source)] = true;
        path.addNode(source);
        path.addEdgeGain(lastGain);

        if(source.equals(destination)) {
            // base case
            forwardPaths.add(path.clone());
        } else {
            for(Edge edge : graph.getEdgesFromNode(source)) {
                if(!visited[graph.getNodeIndex(edge.getDestination())]) {
                    // recursive call
                    dfs(graph, visited, edge.getDestination(), destination, path, edge.getGain());
                }
            }
        }

        // Backtracking
        path.removeLastNodeAndGain(lastGain);
        visited[graph.getNodeIndex(source)] = false;
    }

}
