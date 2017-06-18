package program;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow Graph
 * Created by Bishoy N. Gendy (programajor) on Thursday 20/04/2017.
 */
public class SignalFlowGraph {

    public static Graph graph;

    int nodeID = 0;
    static int edgeCounter = 0;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
                //System.setProperty("org.gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
                initializeGraph();
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Signal Flow Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridLayout(1, 2));

        JLabel solutionLabel = new JLabel();
        solutionLabel.setText("Solution\n");

        JPanel buttonsFlowLayoutPanel = getButtonsFlowLayoutPanel(solutionLabel);

        JPanel gridBagLayoutPanel = getGridBagLayoutPanel(buttonsFlowLayoutPanel, solutionLabel);

        frame.add(getGraphComponent());
        frame.add(gridBagLayoutPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel getButtonsFlowLayoutPanel(JLabel solutionLabel) {
        FlowLayout buttonsFlowLayout = new FlowLayout();
        JPanel buttonsFlowLayoutPanel = new JPanel(buttonsFlowLayout);
        buttonsFlowLayoutPanel.add(getAddNewNodeButton());
        buttonsFlowLayoutPanel.add(getAddNewEdgeButton());
        buttonsFlowLayoutPanel.add(getOverallTransferFunctionButton(solutionLabel));
        return buttonsFlowLayoutPanel;
    }

    private static JPanel getGridBagLayoutPanel(JPanel buttonsFlowLayoutPanel, JLabel solution) {
        JPanel gridBagLayoutPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1;
        gridBagLayoutPanel.add(buttonsFlowLayoutPanel, gridBagConstraints);
        gridBagConstraints.weighty = 100;
        gridBagLayoutPanel.add(solution, gridBagConstraints);
        return gridBagLayoutPanel;
    }

    private static JButton getOverallTransferFunctionButton(final JLabel solution) {
        JButton getOverallTransferFunctionButton = new JButton("Get Overall Transfer Function");
        getOverallTransferFunctionButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createGetOverallTransferFunctionFrame(solution);
            }
        });
        return getOverallTransferFunctionButton;
    }

    private static void createGetOverallTransferFunctionFrame(JLabel solution) {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                final JFrame frame = new JFrame("Get Overall Transfer Function");
                JPanel componentsPanel = new JPanel();
                componentsPanel.setLayout(new GridLayout(3,1, 10,10));
                JPanel labelsPanel = getOverallTransferFunctionLabelPanel();
                componentsPanel.add(labelsPanel);
                final JComboBox sourceComboBox = getFilledComboBox();
                final JComboBox destinationComboBox = getFilledComboBox();
                JPanel dataInputPanel = getOverallTransferFunctionDataInputPanel(sourceComboBox, destinationComboBox);
                componentsPanel.add(dataInputPanel);
                JButton submit = getOverallTransferFunctionSubmitButton(frame,
                        sourceComboBox, destinationComboBox, solution);
                componentsPanel.add(submit);
                makeFrameReady(frame, componentsPanel);
            }
        });
    }

    private static JPanel getOverallTransferFunctionDataInputPanel(JComboBox sourceComboBox, JComboBox destinationComboBox) {
        JPanel dataInputPanel = new JPanel();
        dataInputPanel.setLayout(new GridLayout(1,2, 10,0));
        dataInputPanel.add(sourceComboBox);
        dataInputPanel.add(destinationComboBox);
        return dataInputPanel;
    }

    private static JButton getOverallTransferFunctionSubmitButton(final JFrame frame,
                      final JComboBox sourceComboBox, final JComboBox destinationComboBox, final JLabel solution) {
        JButton submit = new JButton("Get Overall Transfer Function");
        submit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraphAdapter graphAdapter = new GraphAdapter();
                if(sourceComboBox.getItemCount() == 0 ||
                    destinationComboBox.getItemCount() == 0) {
                    solution.setText("<html>Solution<br>Empty Graph</html>");
                } else {
                    solution.setText("<html>Solution<br>" + graphAdapter.solve(graph,
                            graph.getNode(sourceComboBox.getSelectedItem().toString()),
                            graph.getNode(destinationComboBox.getSelectedItem().toString())) + "</html>");
                }
                frame.setVisible(false);
            }
        });
        return submit;
    }

    private static JPanel getOverallTransferFunctionLabelPanel() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(1,2, 10,0));
        labelsPanel.add(new JLabel("Input Node"));
        labelsPanel.add(new JLabel("Output Node"));
        return labelsPanel;
    }

    private static void createAddEdgeFrame() {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                final JFrame frame = new JFrame("Add New Edge");

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(3,1, 10,10));

                JPanel labelsPanel = getNewEdgeLabelsPanel();
                inputPanel.add(labelsPanel);

                final JComboBox sourceComboBox = getFilledComboBox();
                final JComboBox destinationComboBox = getFilledComboBox();
                final JTextField costTextField = new JTextField(20);
                costTextField.requestFocus();

                JPanel dataInputPanel = getNewEdgeDataInputPanel(sourceComboBox, destinationComboBox, costTextField);
                inputPanel.add(dataInputPanel);

                JButton submitNewEdgeButton = getAddNewEdgeSubmitButton(frame, sourceComboBox, destinationComboBox, costTextField);
                inputPanel.add(submitNewEdgeButton);
                makeFrameReady(frame, inputPanel);
            }
        });
    }

    private static JPanel getNewEdgeDataInputPanel(JComboBox sourceComboBox, JComboBox destinationComboBox, JTextField costTextField) {
        JPanel dataInputPanel = new JPanel();

        dataInputPanel.setLayout(new GridLayout(1,3, 10,0));
        dataInputPanel.add(sourceComboBox);
        dataInputPanel.add(destinationComboBox);

        dataInputPanel.add(costTextField);
        return dataInputPanel;
    }

    private static JPanel getNewEdgeLabelsPanel() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(1,3, 10,0));

        labelsPanel.add(new JLabel("From"));
        labelsPanel.add(new JLabel("To"));
        labelsPanel.add(new JLabel("Cost"));
        return labelsPanel;
    }

    private static JButton getAddNewEdgeSubmitButton(final JFrame frame, final JComboBox sourceComboBox, final JComboBox destinationComboBox, final JTextField costTextField) {
        JButton submitNewEdgeButton = new JButton("Add New Edge");
        submitNewEdgeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cost = costTextField.getText().trim();
                if(!cost.equals("")) {
                    double weight = Double.valueOf(cost);
                    Edge edge = graph.addEdge(edgeCounter + "", sourceComboBox.getSelectedItem().toString(),
                            destinationComboBox.getSelectedItem().toString(), true);
                    edgeCounter++;
                    edge.setAttribute("weight", weight);
                    edge.addAttribute("ui.label", weight);
                    frame.setVisible(false);
                }
            }
        });
        return submitNewEdgeButton;
    }

    private static void makeFrameReady(JFrame frame, JPanel inputPanel) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        JPanel totalPanel = new JPanel();
        totalPanel.setOpaque(true);
        totalPanel.add(inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, totalPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }

    private static void createAddNodeFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                final JFrame frame = new JFrame("Add New Node");
                JPanel componentsPanel = new JPanel();
                componentsPanel.setLayout(new GridLayout(1,2, 10,20));
                final JTextField nodeTextField = new JTextField(20);
                nodeTextField.requestFocus();
                JButton submitNewNode = getAddNewNodeSubmitButton(frame, nodeTextField);
                componentsPanel.add(nodeTextField);
                componentsPanel.add(submitNewNode);
                makeFrameReady(frame, componentsPanel);
            }
        });
    }

    private static JButton getAddNewNodeSubmitButton(final JFrame frame, final JTextField nodeTextField) {
        JButton submitNewNode = new JButton("Add New Node");
        submitNewNode.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!nodeTextField.getText().trim().equals("")) {
                    graph.addNode(nodeTextField.getText().trim());
                    graph.getNode(nodeTextField.getText().trim()).addAttribute("ui.label",
                            nodeTextField.getText().trim());
                    nodeTextField.setText("");
                    frame.setVisible(false);
                }
            }
        });
        return submitNewNode;
    }

    private static JButton getAddNewNodeButton() {
        JButton button = new JButton("Add New Node");
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAddNodeFrame();
            }
        });
        return button;
    }

    private static JButton getAddNewEdgeButton() {
        JButton button = new JButton("Add New Edge");
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAddEdgeFrame();
            }
        });
        return button;
    }

    private static JComboBox getFilledComboBox() {
        final JComboBox comboBox = new JComboBox();
        for(int i = 0; i < graph.getNodeCount(); i++) {
            comboBox.addItem(graph.getNode(i).getId());
        }
        return comboBox;
    }

    private static Component getGraphComponent() {
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        View view = viewer.addDefaultView(false);
        viewer.enableAutoLayout();
        return (Component) view;
    }

    private static void initializeGraph() {
        graph = new MultiGraph("Signal Flow Graph");
        graph.setStrict(false);
        graph.setAutoCreate(false);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "node {\n" +
                "\tfill-mode: plain;  \n" +
                "\tstroke-mode: plain;\n" +
                "\tstroke-color: black;\n" +
                "\ttext-color: blue;\n" +
                "\ttext-size: 20;\n" +
                "\ttext-alignment: above;\n" +
                "}\n" +
                "edge {\n" +
                "\ttext-size: 20;\n" +
                "\ttext-color: red;\n" +
                "\ttext-alignment: justify;\n" +
                "}");
    }
}
