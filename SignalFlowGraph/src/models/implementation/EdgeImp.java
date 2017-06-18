package models.implementation;

import models.Edge;
import models.Node;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public class EdgeImp implements Edge {

    /**
     * gain of the edge.
     */
    private double gain;

    /**
     * source node of the edge.
     */
    private Node source;

    /**
     * destination node of the edge.
     */
    private Node destination;

    public EdgeImp() { }

    public EdgeImp(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public EdgeImp(double gain, Node source, Node destination) {
        this.gain = gain;
        this.source = source;
        this.destination = destination;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }
}
