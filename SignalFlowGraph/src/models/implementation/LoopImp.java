package models.implementation;

import models.Loop;
import models.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/18/17.
 */
public class LoopImp implements Loop{

    /**.
     * The nodes in this loop
     */
    private List<Node> nodes;

    /**.
     * The gain of the loop
     */
    private int loopGain;

    public LoopImp() {
        nodes = new LinkedList<>();
    }

    @Override
    public Boolean intersects(Node node) {
        return nodes.contains(node);
    }

    @Override
    public Boolean intersects(Loop loop) {
        List<Node> secondaryNodesList = loop.getNodes();
        for (Node node : nodes) {
            for (Node secondaryNode : secondaryNodesList) {
                if (node.equals(secondaryNode)) return true;
            }
        }
        return false;
    }

    @Override
    public int getGain() {
        return loopGain;
    }

    @Override
    public void setGain(int gain) {
        this.loopGain = gain;
    }

    @Override
    public void addNode(Node node) {
        if (nodes.contains(node)) throw new IllegalArgumentException("Node already added!!");
        nodes.add(node);
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopImp loopImp = (LoopImp) o;

        if (loopGain != loopImp.loopGain) return false;
        for (Node node : nodes) {
            if (!loopImp.nodes.contains(node)) {
                return false;
            }
        }
        return nodes.size() == loopImp.nodes.size();
    }

    @Override
    public int hashCode() {
        int result = nodes.hashCode();
        result = 31 * result + loopGain;
        return result;
    }

    @Override
    public String toString() {
        return "LoopImp{" +
                "nodes=" + nodes +
                ", loopGain=" + loopGain +
                '}';
    }
}
