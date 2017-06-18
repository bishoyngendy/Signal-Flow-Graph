package engine.implementation;

import engine.LoopsGetter;
import models.Edge;
import models.Graph;
import models.Loop;
import models.Node;
import models.implementation.LoopImp;

import java.util.*;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/18/17.
 * This class is responsible for getting all loops in a given graph
 */
public class LoopsGetterImp implements LoopsGetter {

    /**.
     * List of all found loops
     */
    private List<Loop> loops;
    /**.
     * A set containing the visited loops
     */
    private Set<Node> visited;

    @Override
    public List<Loop> getLoops(Graph graph) {
        initializePrivateData();
        Stack<Edge> path = new Stack<>();
        dfs(graph, graph.getNodes().get(0), path);
        return loops;
    }

    /**.
     * Initialize private data
     */
    private void initializePrivateData() {
        loops = new LinkedList<>();
        visited = new HashSet<>();
    }

    /**.
     * A DFS recursive call to found the loop, it runs until it find a visited node
     * then it add this loop to the loops list.
     * @param graph the graph to work on.
     * @param node current node in the graph.
     * @param currentPath current path including all taken nodes and edges.
     */
    private void dfs (Graph graph, Node node, Stack<Edge> currentPath) {
        if (visited.contains(node)) { // find loop
            addLoopIfNew(node, currentPath);
            return;
        }

        List<Edge> edgeList = graph.getEdgesFromNode(node);
        for (Edge edge : edgeList) {
            Node dest = edge.getDestination();
            Node src = edge.getSource();
            visited.add(src);
            currentPath.add(edge);
            dfs(graph, dest, currentPath);
            visited.remove(src);
            currentPath.remove(edge);
        }
    }

    /**.
     * It add the found loop if not already added.
     * @param node current node in the graph.
     * @param currentPath current path including all taken nodes and edges.
     */
    private void addLoopIfNew(Node node, Stack<Edge> currentPath) {
        boolean found = false;
        int gain = 1;
        Loop loop = new LoopImp();
        for (Edge edge : currentPath) {
            if (edge.getSource() == node) {
                found = true;
            }

            if (found) {
                loop.addNode(edge.getSource());
                gain *= edge.getGain();
            }
        }
        loop.setGain(gain);
        if (!loops.contains(loop))
            this.loops.add(loop);
    }
}
