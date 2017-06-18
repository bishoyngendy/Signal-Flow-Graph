package engine.implementation;

import engine.DeltaGetter;
import engine.Evaluator;
import engine.ForwardPathsGetter;
import engine.LoopsGetter;
import models.*;
import models.implementation.EdgeImp;
import models.implementation.GraphImp;
import models.implementation.NodeImp;

import java.util.LinkedList;
import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 */
public class EvaluatorImp implements Evaluator{

    @Override
    public String getResultWithSteps(Graph graph, Node source, Node destination) {
        // initialization
        StringBuilder builder = new StringBuilder();
        ForwardPathsGetter forwardPathsGetter = new ForwardPathsGetterImp();
        LoopsGetter loopsGetter = new LoopsGetterImp();

        // get data
        List<ForwardPath> forwardPathList = forwardPathsGetter.getForwardPaths(graph, source, destination);
        List<Loop> loopList = loopsGetter.getLoops(graph);
        DeltaGetter deltaGetter = new DeltaGetterImp(loopList);
        List<Double> deltas = getDeltas(forwardPathList, deltaGetter);
        DeltaValue deltaValue = deltaGetter.getDeltaValue();

//        List<Loop> loopCombinations =
        builder.append(getForwardPathsData(forwardPathList)).append("<br>");

        builder.append(getLoopsData(loopList)).append("<br>");

        builder.append(getDeltasData(deltas)).append("<br>");

        builder.append("Delta: ").append(deltaValue.getDeltaValue()).append("<br><br>");

        List<List<List<Loop>>> loopsCombinations = deltaValue.getLoopsCombination();

        builder.append(getLoopsCombinationsData(loopsCombinations));
        builder.append("<br>").append("Overall Transfer Function: ").append(getTotalGain(forwardPathList, deltas, deltaValue.getDeltaValue()));

        return builder.toString();
    }

    private String getLoopsCombinationsData(List<List<List<Loop>>> loopsCombinations) {
        StringBuilder builder = new StringBuilder();
        builder.append("Loops Combinations for delta: ").append("<br>");

        for (int i = 1; i < loopsCombinations.size(); i++) {
            builder.append(i + 1).append(" untouched loops. <br>");
            for (List<Loop> list : loopsCombinations.get(i)) {
                for (int j = 0; j < list.size(); j++) {
                    Loop loop = list.get(j);
                    if (j != 0)
                        builder.append(", ");
                    for (Node node : loop.getNodes()) {
                        builder.append(node.getTitle()).append(" ");
                    }
                }
                builder.append("<br>");
            }
        }

        return builder.toString();
    }


    private String getForwardPathsData(List<ForwardPath> forwardPaths) {
        StringBuilder builder = new StringBuilder();
        builder.append("Forward Paths:");
        builder.append("<br>");
        for (ForwardPath forwardPath : forwardPaths) {
            builder.append(getForwardPathData(forwardPath));
//            builder.append("<br>");
//            builder.append("<br>");
        }

        return builder.toString();
    }

    private String getForwardPathData(ForwardPath forwardPath) {
        List<Node> nodes = forwardPath.getNodes();
        StringBuilder builder = new StringBuilder();
        for (Node node : nodes) {
            builder.append(node.getTitle()).append(" ");
        }

//        builder.append("<br>");
        builder.append("&nbsp&nbsp Gain: ").append(forwardPath.getTotalGain()).append("<br>");
        return builder.toString();
    }

    private String getLoopsData(List<Loop> loops) {
        StringBuilder builder = new StringBuilder();
        builder.append("Loops: ");
        builder.append("<br>");
        for (Loop loop : loops) {
            builder.append(getLoopData(loop));
//            builder.append("<br>");
//            builder.append("<br>");
        }

        return builder.toString();
    }

    private String getLoopData(Loop loop) {
        StringBuilder builder = new StringBuilder();
        List<Node> nodes = loop.getNodes();
        for (Node node : nodes) {
            builder.append(node.getTitle()).append(" ");
        }

//        builder.append("<br>");
        builder.append("&nbsp&nbsp Gain: ").append(loop.getGain()).append("<br>");
        return builder.toString();
    }

    private List<Double> getDeltas(List<ForwardPath> forwardPaths, DeltaGetter deltaGetter) {
        List<Double> deltas = new LinkedList<>();
        for (ForwardPath forwardPath : forwardPaths) {
            deltas.add(deltaGetter.getDeltaValue(forwardPath));
        }
        return deltas;
    }

    private String getDeltasData(List<Double> delta) {
        StringBuilder builder = new StringBuilder();
        builder.append("Deltas: ").append("<br>");

        for (int i = 0; i < delta.size(); i++) {
            builder.append("Delta ").append(i + 1).append(" = ").append(delta.get(i))
                    .append("<br>");
        }

        return builder.toString();
    }

    private double getTotalGain(List<ForwardPath> forwardPaths, List<Double> deltas, double delta) {
        double value = 0;
        for (int i = 0; i < forwardPaths.size(); i++) {
            value += forwardPaths.get(i).getTotalGain() * deltas.get(i);
        }
        value /= delta;

        return value;
    }

    public static void main(String[] args) {
        Evaluator evaluator = new EvaluatorImp();
        Graph g = new GraphImp();
//        Node node1 = new NodeImp("1");
//        Node node2 = new NodeImp("11");
//
//        g.addNode(node1);
//        for(int i = 2; i < 11; i++) {
//            g.addNode(new NodeImp(Integer.toString(i)));
//        }
//        g.addNode(node2);
//
//        g.addEdge(new EdgeImp(1, g.getNode("1"), g.getNode("2")));
//        g.addEdge(new EdgeImp(10, g.getNode("2"), g.getNode("7")));
//        g.addEdge(new EdgeImp(5, g.getNode("2"), g.getNode("3")));
//        g.addEdge(new EdgeImp(10, g.getNode("3"), g.getNode("4")));
//        g.addEdge(new EdgeImp(-1, g.getNode("4"), g.getNode("3")));
//        g.addEdge(new EdgeImp(2, g.getNode("4"), g.getNode("5")));
//        g.addEdge(new EdgeImp(-2, g.getNode("5"), g.getNode("4")));
//        g.addEdge(new EdgeImp(-1, g.getNode("5"), g.getNode("2")));
//        g.addEdge(new EdgeImp(1, g.getNode("5"), g.getNode("6")));
//        g.addEdge(new EdgeImp(3, g.getNode("6"), g.getNode("7")));
//        g.addEdge(new EdgeImp(-5, g.getNode("7"), g.getNode("1")));
//        g.addEdge(new EdgeImp(-3, g.getNode("7"), g.getNode("7")));
//        g.addEdge(new EdgeImp(2, g.getNode("7"), g.getNode("8")));
//        g.addEdge(new EdgeImp(3, g.getNode("8"), g.getNode("9")));
//        g.addEdge(new EdgeImp(4, g.getNode("9"), g.getNode("10")));
//        g.addEdge(new EdgeImp(1, g.getNode("10"), g.getNode("11")));
//        g.addEdge(new EdgeImp(-2, g.getNode("9"), g.getNode("8")));
//        g.addEdge(new EdgeImp(-4, g.getNode("11"), g.getNode("10")));
//
//        System.out.println(evaluator.getResultWithSteps(g, node1, node2));

        Node node1 = new NodeImp("0");
        Node node2 = new NodeImp("5");

        g.addNode(node1);
        for(int i = 1; i < 5; i++) {
            g.addNode(new NodeImp(Integer.toString(i)));
        }
        g.addNode(node2);
        g.addNode(new NodeImp("6"));

        g.addEdge(new EdgeImp(1, g.getNode("0"), g.getNode("1")));
        g.addEdge(new EdgeImp(5, g.getNode("1"), g.getNode("2")));
        g.addEdge(new EdgeImp(10, g.getNode("2"), g.getNode("3")));
        g.addEdge(new EdgeImp(2, g.getNode("3"), g.getNode("4")));
        g.addEdge(new EdgeImp(1, g.getNode("4"), g.getNode("5")));
        g.addEdge(new EdgeImp(10, g.getNode("1"), g.getNode("6")));
        g.addEdge(new EdgeImp(2, g.getNode("6"), g.getNode("4")));
        g.addEdge(new EdgeImp(-1, g.getNode("6"), g.getNode("6")));
        g.addEdge(new EdgeImp(-2, g.getNode("4"), g.getNode("3")));
        g.addEdge(new EdgeImp(-1, g.getNode("3"), g.getNode("2")));
        g.addEdge(new EdgeImp(-1, g.getNode("4"), g.getNode("1")));

        System.out.println(evaluator.getResultWithSteps(g, node1, node2));
    }
}
