package models.implementation;

import models.Edge;
import models.Graph;
import models.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public class GraphImp implements Graph {

    /**
     * nodes of the graph.
     */
    private List<Node> nodes;

    /**
     * edges of the graph.
     */
    private List<Edge> edges;

    /**
     * adjacency list of the graph.
     */
    private List<List<Edge>> adjList;

    public GraphImp() {
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
        this.adjList = new ArrayList<List<Edge>>();
    }

    public void addNode(Node node) {
        nodes.add(node);
        adjList.add(new ArrayList<Edge>());
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        Node source = edge.getSource();
        int ind = 0;
        for(int i = 0; i <  nodes.size(); i++) {
            if (nodes.get(i).getTitle().equals(source.getTitle())) {
                ind = i;
                break;
            }
        }
        adjList.get(ind).add(edge);
    }

    public Boolean contains(Node node) {
        return getNode(node.getTitle()) == null ? false : true;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdgesFromNode(Node node) {
        int ind = getNodeIndex(node);
        return adjList.get(ind);
    }

    public int getNodeIndex(Node node) {
        for(int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getTitle().equals(node.getTitle())) {
                return i;
            }
        }
        throw new RuntimeException("error");
    }

    public Node getNode(String title) {
        for (Node node : nodes) {
            if(node.getTitle().equals(title)) {
                return node;
            }
        }
        return null;
    }

    public int getNodesCount() {
        return nodes.size();
    }

    public int getEdgesCount() {
        return edges.size();
    }
}
