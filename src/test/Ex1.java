package test;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ron on 7/23/16.
 */
public class Ex1 {

    public static void main(String[] args) {
        final mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

                    Object start = graph.insertVertex(parent, "start", "start", 100,
                100, 80, 30, "shape=hexagon");
            for (int i = 0; i < 10; i++) {
                Object a = graph.insertVertex(parent, "A" + i, "A" + i, 100,
                        100, 80, 30);
                graph.insertEdge(parent, null, "E" + i, start, a, "rotation=180");

                Object b = graph.insertVertex(parent, "B" + i, "B" + i, 100,
                        100, 80, 30, "shape=rhombus");

                graph.insertEdge(parent, null, "E" + i, a, b);
                start = a;
            }
        new mxCompactTreeLayout(graph, false).execute(graph.getDefaultParent());
        try {
            BufferedImage bufferedImage = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
            File output = new File("/tmp/test.png");
            ImageIO.write(bufferedImage, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
