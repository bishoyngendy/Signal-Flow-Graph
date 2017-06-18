package models.implementation;

import models.ForwardPath;
import models.Loop;
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
public class ForwardPathImp implements ForwardPath {

    /**
     * total gain of the forward path.
     */
    private double totalGain;

    /**
     * nodes of the forward path.
     */
    private List<Node> nodes;

    public ForwardPathImp() {
        this.nodes = new ArrayList<Node>();
        this.totalGain = 1;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdgeGain(double gain) {
        totalGain *= gain;
    }

    public void removeLastNodeAndGain(double lastGain) {
        nodes.remove(nodes.size()-1);
        totalGain /= lastGain;
    }

    public ForwardPath clone() {
        ForwardPath forwardPath = new ForwardPathImp();
        forwardPath.setNodes(new ArrayList<Node>(getNodes()));
        forwardPath.setTotalGain(getTotalGain());
        return forwardPath;
    }

    public double getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(double totalGain) {
        this.totalGain = totalGain;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean intersects(Loop loop) {
        List<Node> secondaryNodesList = loop.getNodes();
        for (Node node : nodes) {
            for (Node secondaryNode : secondaryNodesList) {
                if (node.equals(secondaryNode)) return true;
            }
        }
        return false;
    }
}
