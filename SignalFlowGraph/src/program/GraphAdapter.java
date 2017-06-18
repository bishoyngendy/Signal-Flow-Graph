package program;

import engine.Evaluator;
import engine.implementation.EvaluatorImp;
import models.implementation.EdgeImp;
import models.implementation.GraphImp;
import models.implementation.NodeImp;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Thursday 20/04/2017.
 */
class GraphAdapter {

    String solve(Graph graph, Node source, Node destination) {
        String validationResult = validateInput(graph, source);
        if (validationResult != null) {
            return validationResult;
        }

        models.Graph newGraph = new GraphImp();
        models.Node newSource = new NodeImp();
        models.Node newDestination = new NodeImp();
        for(int i = 0; i < graph.getNodeCount(); i++) {
            Node oldNode = graph.getNode(i);
            models.Node newNode = new NodeImp(oldNode.getId());
            if(oldNode.equals(source)) {
                newSource = newNode;
            }
            if(oldNode.equals(destination)) {
                newDestination = newNode;
            }
            newGraph.addNode(newNode);
        }

        for(int i = 0; i < graph.getEdgeCount(); i++) {
            Edge oldEdge = graph.getEdge(i);
            models.Edge newEdge = new EdgeImp();
            newEdge.setGain(Integer.valueOf((int) oldEdge.getNumber("weight")));
            newEdge.setSource(newGraph.getNode(oldEdge.getSourceNode().getId()));
            newEdge.setDestination(newGraph.getNode(oldEdge.getTargetNode().getId()));
            newGraph.addEdge(newEdge);
        }

        Evaluator evaluator = new EvaluatorImp();

        return evaluator.getResultWithSteps(newGraph, newSource, newDestination);
    }

    private String validateInput(Graph graph, Node source) {
        ConnectedComponents cc = new ConnectedComponents();
        cc.init(graph);

        if(cc.getConnectedComponentsCount() != 1) {
            return "Graph must contain only one strongly connected component";
        }

        return null;
    }

}