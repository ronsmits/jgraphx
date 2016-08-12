package test;

import java.awt.*;

import javax.swing.*;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;

public class Example extends JFrame {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800, 800);
        f.setLocation(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final mxGraph graph = new mxGraph();

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        f.getContentPane().add(graphComponent, BorderLayout.CENTER);

        f.setVisible(true);
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent, null, "Hello,", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "World!", 200, 150, 80, 30);
            Object e1 = graph.insertEdge(parent, null, "", v1, v2);
        }
        finally
        {
            // Updates the display
            graph.getModel().endUpdate();
        }
//        try {
//            Object start = graph.insertVertex(parent, "start", "start", 100,
//                100, 80, 30, "shape=hexagon");
//            for (int i = 0; i < 10; i++) {
//                Object a = graph.insertVertex(parent, "A" + i, "A" + i, 100,
//                    100, 80, 30);
//                graph.insertEdge(parent, null, "E" + i, start, a, "rotation=180");
//
//                Object b = graph.insertVertex(parent, "B" + i, "B" + i, 100,
//                    100, 80, 30, "shape=rhombus");
//
//                graph.insertEdge(parent, null, "E" + i, a, b);
//                start = a;
//            }
//        } finally {
//            graph.getModel().endUpdate();
//        }

        //morphGraph(graph, graphComponent);
        new mxCompactTreeLayout(graph, false).execute(graph.getDefaultParent());
    }

    private static void morphGraph(
        final mxGraph graph,
        mxGraphComponent graphComponent) {
        // define layout
        mxIGraphLayout layout = new mxFastOrganicLayout(graph);

        // layout using morphing
        graph.getModel().beginUpdate();
        try {
            layout.execute(graph.getDefaultParent());
        } finally {
            mxMorphing morph = new mxMorphing(graphComponent, 20, 1.5, 20);

            morph.addListener(mxEvent.DONE, new mxEventSource.mxIEventListener() {

                @Override
                public void invoke(Object arg0, mxEventObject arg1) {
                    graph.getModel().endUpdate();
                    // fitViewport();
                }

            });

            morph.startAnimation();
        }

    }
}
