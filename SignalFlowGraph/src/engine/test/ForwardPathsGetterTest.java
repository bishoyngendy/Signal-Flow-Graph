package engine.test;

import engine.ForwardPathsGetter;
import engine.implementation.ForwardPathsGetterImp;
import models.ForwardPath;
import models.Graph;
import models.implementation.EdgeImp;
import models.implementation.GraphImp;
import models.implementation.NodeImp;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Friday 21/04/2017.
 */
class ForwardPathsGetterTest {
    @Test
    void getForwardPaths() {
        Graph g = new GraphImp();
        int n = 7;
        for(int i = 0; i < n; i++) {
            g.addNode(new NodeImp("" + i+1));
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
        g.addEdge(new EdgeImp(-1, g.getNode("7"), g.getNode("7")));
        g.addEdge(new EdgeImp(2, g.getNode("7"), g.getNode("5")));

        ForwardPathsGetter forwardPathsGetter = new ForwardPathsGetterImp();
        String source = "1";
        String destination = "6";
        List<ForwardPath> forwardPaths = forwardPathsGetter.getForwardPaths(g, g.getNode(source),
                g.getNode(destination));

        for(ForwardPath path : forwardPaths) {
            for(int i = 0; i < path.getNodes().size(); i++) {
                System.out.print(path.getNodes().get(i).getTitle() + " ");
            }
            System.out.println("Gain : " + path.getTotalGain());
        }
    }

}