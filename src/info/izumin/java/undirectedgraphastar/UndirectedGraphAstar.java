package info.izumin.java.undirectedgraphastar;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphAstar {

    private static WeightedGraph graph;
    private static final int[] h = {110, 80, 65, 70, 35, 30, 75, 50, 25, 0};
    private static final List<WeightedEdge> edgeList = new ArrayList<WeightedEdge>(){
        private static final long serialVersionUID = 1L;
        {
            add(new WeightedEdge(0, 1, 45)); add(new WeightedEdge(0, 3, 40));
            add(new WeightedEdge(0, 6, 45)); add(new WeightedEdge(1, 2, 45));
            add(new WeightedEdge(1, 4, 50)); add(new WeightedEdge(2, 4, 35));
            add(new WeightedEdge(3, 4, 45)); add(new WeightedEdge(3, 7, 20));
            add(new WeightedEdge(4, 5, 25)); add(new WeightedEdge(4, 7, 30));
            add(new WeightedEdge(6, 7, 40)); add(new WeightedEdge(7, 8, 20));
            add(new WeightedEdge(8, 9, 25));
        }
    };

    public static void main(String[] args) {
        graph = new WeightedGraph();
        initGraph();
        AstarSearch astar = new AstarSearch(graph);
        if (astar.search()) System.out.println(graph.getPath());
    }

    private static void initGraph() {
        for (int i = 0; i < h.length; i++) {
            graph.add(new Node(i, h[i], h[i] == 0));
        }
        for (WeightedEdge edge : edgeList) {
            int[] v = edge.getVertex();
            Node[] n = {graph.get(v[0]), graph.get(v[1])};
            int w = edge.getWeight();
            n[0].setChild(v[1], w); n[1].setChild(v[0], w);
        }
    }

}
