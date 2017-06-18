package models.test;

import models.Loop;
import models.Node;
import models.implementation.LoopImp;
import models.implementation.NodeImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 */
class LoopImpTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void intersects() {
        Node node1 = new NodeImp("1");
        Node node2 = new NodeImp("2");
        Node node3 = new NodeImp("3");
        Node node4 = new NodeImp("4");
        Node node5 = new NodeImp("5");
        Node node6 = new NodeImp("6");

        Loop loop1 = new LoopImp();
        loop1.addNode(node1);
        loop1.addNode(node2);
        loop1.addNode(node3);
        loop1.addNode(node4);

        assertTrue(loop1.intersects(loop1), "Loop1 touches itself");

        Loop loop2 = new LoopImp();
        loop2.addNode(node1);
        loop2.addNode(node5);
        loop2.addNode(node6);

        assertTrue(loop1.intersects(loop2), "Loop1 touches loop2");
        assertTrue(loop2.intersects(loop1), "Loop2 touches loop1");

        Loop loop3 = new LoopImp();
        loop3.addNode(node5);
        loop3.addNode(node6);

        assertFalse(loop1.intersects(loop3), "Loop1 touches loop3");
        assertFalse(loop3.intersects(loop1), "Loop3 touches loop1");
    }

    @Test
    void addNode() {
        Loop loop = new LoopImp();
        Node node1 = new NodeImp("1");
        Node node2 = new NodeImp("2");
        Node node3 = new NodeImp("3");
        loop.addNode(node1);
        loop.addNode(node2);
        loop.addNode(node3);

        assertThrows(IllegalArgumentException.class, ()->{
            loop.addNode(node1);
        } );
    }

}