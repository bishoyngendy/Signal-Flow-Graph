package models.implementation;

import models.Node;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Tuesday 18/04/2017.
 */
public class NodeImp implements Node {

    /**
     * title of the node
     */
    private String title;

    public NodeImp() { }

    public NodeImp(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeImp nodeImp = (NodeImp) o;

        return title.equals(nodeImp.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return title;
    }
}
