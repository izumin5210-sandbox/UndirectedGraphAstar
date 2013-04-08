package info.izumin.java.undirectedgraphastar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AstarSearch {

    private final WeightedGraph graph;
    /**
     * Key : n
     * Value : f^*(n) = g^*(n) + h^*(n)
     */
    private Map<Node, Integer>
            open = new HashMap<Node, Integer>(),
            closed = new HashMap<Node, Integer>();

    public AstarSearch(WeightedGraph graph) {
        this.graph = graph;
        open.put(graph.get(0), graph.get(0).getHeuristic());
    }

    public boolean search() {
        if (open.isEmpty()) return false;
        Map.Entry<Node, Integer> next = getNextNode(open);
        if (next.getKey().isTerminal()) return true;
        close(next);
        for (Map.Entry<Integer, Integer> entry : next.getKey().getChildSet()) {
            Node adjacent = graph.get(entry.getKey());
            // f'(m) = g^*(n) + h^*(m) + COST(n,m)
            int cost = entry.getValue() + adjacent.getHeuristic()
                    + next.getValue() - next.getKey().getHeuristic();
            if (!open.containsKey(adjacent) && !closed.containsKey(adjacent)) {
                open.put(adjacent, cost);
                adjacent.setParent(next.getKey());
            } else if (open.containsKey(adjacent) && cost < open.get(adjacent)) {
                open.remove(adjacent);
                open.put(adjacent, cost);
                adjacent.setParent(next.getKey());
            } else if (closed.containsKey(adjacent) && cost < closed.get(adjacent)) {
                closed.remove(adjacent);
                open.put(adjacent, cost);
                adjacent.setParent(next.getKey());
            }
        }
        display(open.entrySet(), "/* ==== open ================ */");
        display(closed.entrySet(), "/* ==== closed ================ */");
        return search();
    }

    private void close(Map.Entry<Node, Integer> entry) {
        closed.put(entry.getKey(), entry.getValue());
        open.remove(entry.getKey());
    }

    private Map.Entry<Node, Integer> getNextNode(Map<Node, Integer> map) {
        Map.Entry<Node, Integer> next = null;
        for (Map.Entry<Node, Integer> entry : map.entrySet()) {
            if (next == null || entry.getValue() < next.getValue()) next = entry;
       }
        return next;
    }

    public void display(Set<Map.Entry<Node, Integer>> map, String msg) {
        System.out.println(msg);
        for (Map.Entry<Node, Integer> entry : map) {
            System.out.println(entry.getKey().toString() + ", " + String.valueOf(entry.getValue()));
        }
    }
}
