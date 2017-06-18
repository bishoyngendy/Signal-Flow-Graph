package engine.test;

import engine.DeltaGetter;
import engine.LoopsGetter;
import engine.implementation.DeltaGetterImp;
import engine.implementation.LoopsGetterImp;
import models.Graph;
import models.Loop;
import models.implementation.EdgeImp;
import models.implementation.GraphImp;
import models.implementation.NodeImp;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 * This is
 */
class DeltaGetterImpTest {
    @Test
    void getDeltaValue() {
        Graph g = new GraphImp();
        for(int i = 1; i <= 11; i++) {
            g.addNode(new NodeImp(Integer.toString(i)));
        }

        g.addEdge(new EdgeImp(1, g.getNode("1"), g.getNode("2")));
        g.addEdge(new EdgeImp(10, g.getNode("2"), g.getNode("7")));
        g.addEdge(new EdgeImp(5, g.getNode("2"), g.getNode("3")));
        g.addEdge(new EdgeImp(10, g.getNode("3"), g.getNode("4")));
        g.addEdge(new EdgeImp(-1, g.getNode("4"), g.getNode("3")));
        g.addEdge(new EdgeImp(2, g.getNode("4"), g.getNode("5")));
        g.addEdge(new EdgeImp(-2, g.getNode("5"), g.getNode("4")));
        g.addEdge(new EdgeImp(-1, g.getNode("5"), g.getNode("2")));
        g.addEdge(new EdgeImp(1, g.getNode("5"), g.getNode("6")));
        g.addEdge(new EdgeImp(3, g.getNode("6"), g.getNode("7")));
        g.addEdge(new EdgeImp(-5, g.getNode("7"), g.getNode("1")));
        g.addEdge(new EdgeImp(-3, g.getNode("7"), g.getNode("7")));
        g.addEdge(new EdgeImp(2, g.getNode("7"), g.getNode("8")));
        g.addEdge(new EdgeImp(3, g.getNode("8"), g.getNode("9")));
        g.addEdge(new EdgeImp(4, g.getNode("9"), g.getNode("10")));
        g.addEdge(new EdgeImp(1, g.getNode("10"), g.getNode("11")));
        g.addEdge(new EdgeImp(-2, g.getNode("9"), g.getNode("8")));
        g.addEdge(new EdgeImp(-4, g.getNode("11"), g.getNode("10")));

        LoopsGetter loopsGetter = new LoopsGetterImp();
        List<Loop> loopList = loopsGetter.getLoops(g);

        DeltaGetter deltaGetter = new DeltaGetterImp(loopList);
        System.out.println(deltaGetter.getDeltaValue());
//        System.out.println(loopList.size());

//        for (Loop loop: loopList) {
//            System.out.println("Loop gain: " + loop.getGain());
//            for (Node node : loop.getNodes()) {
//                System.out.print(node.getTitle() + " ");
//            }
//            System.out.println("\n");
//        }
    }

}